package com.example.pas_25_33;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_25_33.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerFragment extends Fragment {
    private List<ModelPlayer> playerList = new ArrayList<>();
    private PlayerAdapter adapter;
    private RecyclerView recyclerView;
    private String teamName;
    private ProgressBar pbLoading;

    // Constructor untuk menerima team name
    public static PlayerFragment newInstance(String teamName) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putString("team_name", teamName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pemain_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.rvpemain);
        pbLoading = view.findViewById(R.id.pbLoading);


        adapter = new PlayerAdapter(playerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        if (getArguments() != null) {
            teamName = getArguments().getString("team_name");
        }

        if (teamName != null && !teamName.isEmpty()) {
            loadPlayers();
        } else {
            pbLoading.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void loadPlayers() {
        pbLoading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<PlayerResponse> call = api.getPlayerByTeam(teamName);

        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    playerList.clear();
                    playerList.addAll(response.body().getPlayers());
                    adapter.notifyDataSetChanged();
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });
    }

    public void updateTeamName(String newTeamName) {
        this.teamName = newTeamName;
        if (isAdded() && getView() != null) {
            loadPlayers();
        }
    }
}