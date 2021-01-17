package com.moringaschool.football_app.network;

import com.moringaschool.football_app.models.competition.FootballDataCompetitionSearchResponse;
import com.moringaschool.football_app.models.standings.FootballDataStandingSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootBallApi {
    @GET("competitions")
    Call<FootballDataCompetitionSearchResponse> listCompetitions(
            @Query("plan") String plan
    );
    @GET("competitions/{id}/standings")
    Call<FootballDataStandingSearchResponse> listTable(
            @Path("id") String id
    );
}
