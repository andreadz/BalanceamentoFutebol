package balanceamento_futebol.andrefilus.com.balanceamentofutebol.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;

import balanceamento_futebol.andrefilus.com.balanceamentofutebol.R;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.databinding.FragmentSelectPlayersBinding;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.models.SportsCourt;

public class SelectPlayersFragment extends Fragment {

    private static final String S_NAME_SOCCER = "s_name_of_soccer";
    private static final String S_TYPE_COURT = "s_type_of_court";
    private String mTypeCourt;
    private String mSoccerName;
    private OnSelectPlayersFragmentActionListener mListener;
    private FragmentSelectPlayersBinding mFragmentBinding;

    public SelectPlayersFragment() {
    }

    public static SelectPlayersFragment newInstance(String soccerName, String typeCourt) {
        Bundle args = new Bundle();
        args.putString(S_NAME_SOCCER, soccerName);
        args.putString(S_TYPE_COURT, typeCourt);
        SelectPlayersFragment fragment = new SelectPlayersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectPlayersFragmentActionListener) {
            mListener = (OnSelectPlayersFragmentActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSelectPlayersFragmentActionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSoccerName = getArguments().getString(S_NAME_SOCCER);
        mTypeCourt = getArguments().getString(S_TYPE_COURT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_players, container, false);
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setSpinnerPosition();

        mFragmentBinding.ratingBarPlayers.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float note, boolean fromUser) {
                mFragmentBinding.textPlayerNote.setText(String.valueOf(note));
            }
        });

        mFragmentBinding.btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerName = mFragmentBinding.inputPlayerName.getText().toString();
                if(playerName.isEmpty())
                    return;
                if(mFragmentBinding.ratingBarPlayers.getNumStars() == 0)
                    return;
                mFragmentBinding.inputPlayerName.setText("");
                mFragmentBinding.ratingBarPlayers.setProgress(0);

            }
        });
    }

    private void setSpinnerPosition(){
        SportsCourt sportsCourt = new SportsCourt();
        sportsCourt.sportsCourtTypeOfCourt = mTypeCourt;
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, sportsCourt.getPositionsOfCourt(sportsCourt.sportsCourtTypeOfCourt));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFragmentBinding.spinnerPosition.setAdapter(dataAdapter);
    }

    public interface OnSelectPlayersFragmentActionListener {
        void onGenerateTeams();
    }
}
