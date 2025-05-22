package com.example.pas_25_33;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v1/json/3/search_all_teams.php")
    Call<TeamResponse> getTeamsByLeague(@Query("l") String leagueName);

    @GET("api/v1/json/3/lookup_all_players.php?id=133604")
    Call<PlayerResponse> getPlayerByTeam(@Query("l") String playerName);
}

