
package com.moringaschool.football_app.models.standings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("playedGames")
    @Expose
    private Integer playedGames;
    @SerializedName("form")
    @Expose
    private String form;
    @SerializedName("won")
    @Expose
    private Integer won;
    @SerializedName("draw")
    @Expose
    private Integer draw;
    @SerializedName("lost")
    @Expose
    private Integer lost;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("goalsFor")
    @Expose
    private Integer goalsFor;
    @SerializedName("goalsAgainst")
    @Expose
    private Integer goalsAgainst;
    @SerializedName("goalDifference")
    @Expose
    private Integer goalDifference;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Table() {
    }

    /**
     * 
     * @param goalsFor
     * @param form
     * @param lost
     * @param won
     * @param playedGames
     * @param position
     * @param team
     * @param draw
     * @param goalsAgainst
     * @param goalDifference
     * @param points
     */
    public Table(Integer position, Team team, Integer playedGames, String form, Integer won, Integer draw, Integer lost, Integer points, Integer goalsFor, Integer goalsAgainst, Integer goalDifference) {
        super();
        this.position = position;
        this.team = team;
        this.playedGames = playedGames;
        this.form = form;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(Integer playedGames) {
        this.playedGames = playedGames;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

}
