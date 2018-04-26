package balanceamento_futebol.andrefilus.com.balanceamentofutebol.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import balanceamento_futebol.andrefilus.com.balanceamentofutebol.R;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.databinding.ItemPlayerListBinding;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.models.Player;

/**
 * Created by André Filus on 15/04/2018.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private ArrayList<Player> mPlayers = new ArrayList<>();
    private OnPlayerClickListerner mListener;

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = mPlayers.get(position);
        holder.mViewHolderBinding.textNamePlayer.setText(player.playerName);
        holder.mViewHolderBinding.textPlayerPosition.setText(player.playerPosition);
        holder.mViewHolderBinding.textPlayerNote.setText(String.valueOf(player.playerNote));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder  {
        ItemPlayerListBinding mViewHolderBinding;

        PlayerViewHolder(View itemView) {
            super(itemView);
            mViewHolderBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnPlayerClickListerner{

    }
}
