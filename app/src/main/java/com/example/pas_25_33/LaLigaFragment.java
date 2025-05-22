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

public class LaLigaFragment extends Fragment {
    private List<ModelTeam> teamList = new ArrayList<>();
    private TeamAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar pbLoading;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout untuk fragment
        View view = inflater.inflate(R.layout.laliga_fragment, container, false);

        // Initialize views
        pbLoading = view.findViewById(R.id.pbLoading);
        recyclerView = view.findViewById(R.id.rvliga);

        // Setup RecyclerView
        adapter = new TeamAdapter(teamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadTeamData();
    }

    private void loadTeamData() {
        pbLoading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        String leagueName = "Spanish La Liga";
        Call<TeamResponse> call = api.getTeamsByLeague(leagueName);

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    teamList.clear();
                    teamList.addAll(response.body().getTeams());
                    adapter.notifyDataSetChanged();
                } else {

                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }
}