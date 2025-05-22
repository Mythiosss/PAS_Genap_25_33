package com.example.pas_25_33;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tryoutpas_25_33.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<ModelPlayer> playerList;
    private Context context;

    public PlayerAdapter(List<ModelPlayer> playerList) {
        this.playerList = playerList;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.pemain, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        ModelPlayer team = playerList.get(position);
        holder.textViewName.setText(team.getStrPlayer());
        holder.tvNationality.setText(team.getStrNationality());
        holder.tvLahir.setText(team.getDateBorn());
        Glide.with(holder.itemView.getContext())
                .load(team.getStrThumb())
                .into(holder.imageBadge);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewStadium;
        ImageView imageBadge;
        TextView tvNationality;
        TextView tvLahir;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewTeamName);
            tvNationality = itemView.findViewById(R.id.textViewStadium);
            imageBadge = itemView.findViewById(R.id.ivPlayerPicture);
            tvLahir = itemView.findViewById(R.id.textViewDateBorn);
        }
    }
}
