package com.moringaschool.football_app.network;

import com.moringaschool.football_app.models.competition.FootballDataCompetitionSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootBallApi {
    @GET("competitions")
    Call<FootballDataCompetitionSearchResponse> listCompetitions(
            @Query("plan") String plan
    );
//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
}
