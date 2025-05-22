package com.example.pas_25_33;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        pbLoading = findViewById(R.id.pbLoading);
        recyclerView = findViewById(R.id.rvliga);
        adapter = new TeamAdapter(teamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        String leagueName = getIntent().getStringExtra("La Liga");
        Call<TeamResponse> call = api.getTeamsByLeague(leagueName);


        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);

                    teamList.clear();
                    teamList.addAll(response.body().getTeams());
                    adapter.notifyDataSetChanged();
                }
                pbLoading.setVisibility(View.GONE);
            }

            public void onFailure(Call<TeamResponse> call, Throwable t) {

            }
        });
}
