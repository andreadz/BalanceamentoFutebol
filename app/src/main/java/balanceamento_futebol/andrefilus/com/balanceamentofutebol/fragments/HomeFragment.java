package balanceamento_futebol.andrefilus.com.balanceamentofutebol.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import balanceamento_futebol.andrefilus.com.balanceamentofutebol.R;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding mFragmentBinding;
    private String mCourtSelected;
    private static final String ARG_PARAM1 = "param1";

    private OnHomeFragmentActionListener mListener;

    public static HomeFragment newInstance(String param1) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        String teste = getArguments().getString(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setSpinnerCourt();

        mFragmentBinding.btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFragmentBinding.inputNameSoccer.getText().toString().isEmpty())
                    return;
                if(mCourtSelected == null || mCourtSelected.isEmpty())
                    return;
                mListener.onHomeFragmentSelectCourt(mFragmentBinding.inputNameSoccer.getText().toString(), mCourtSelected);
            }
        });


        mFragmentBinding.spinnerCourt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0)
                    mCourtSelected = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentActionListener) {
            mListener = (OnHomeFragmentActionListener) context;
        } else {
            throw new ClassCastException("You must implement OnHomeFragmentActionListener on " + context.getClass()
                    + " to make this work");
        }
    }

    private void setSpinnerCourt(){
        List<String> list = new ArrayList<String>();
        list.add("Selecione um tipo de quadra");
        list.add("Society");
        list.add("Salão");
        list.add("Grama");
        list.add("Areia");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFragmentBinding.spinnerCourt.setAdapter(dataAdapter);
    }

    public interface OnHomeFragmentActionListener {
        void onHomeFragmentSelectCourt(@NonNull String name, @NonNull String typeCourt);
    }
}
