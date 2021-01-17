
package com.moringaschool.football_app.models.standings;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootballDataStandingSearchResponse {

    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("competition")
    @Expose
    private Competition competition;
    @SerializedName("season")
    @Expose
    private Season season;
    @SerializedName("standings")
    @Expose
    private List<Standing> standings = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FootballDataStandingSearchResponse() {
    }

    /**
     * 
     * @param season
     * @param competition
     * @param filters
     * @param standings
     */
    public FootballDataStandingSearchResponse(Filters filters, Competition competition, Season season, List<Standing> standings) {
        super();
        this.filters = filters;
        this.competition = competition;
        this.season = season;
        this.standings = standings;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }

}
