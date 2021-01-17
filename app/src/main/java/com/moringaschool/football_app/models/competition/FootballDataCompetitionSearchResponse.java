
package com.moringaschool.football_app.models.competition;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootballDataCompetitionSearchResponse {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("competitions")
    @Expose
    private List<Competition> competitions = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FootballDataCompetitionSearchResponse() {
    }

    /**
     * 
     * @param count
     * @param competitions
     * @param filters
     */
    public FootballDataCompetitionSearchResponse(Integer count, Filters filters, List<Competition> competitions) {
        super();
        this.count = count;
        this.filters = filters;
        this.competitions = competitions;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

}
