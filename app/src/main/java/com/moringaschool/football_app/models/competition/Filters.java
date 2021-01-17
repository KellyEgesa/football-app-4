
package com.moringaschool.football_app.models.competition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {

    @SerializedName("plan")
    @Expose
    private String plan;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Filters() {
    }

    /**
     * 
     * @param plan
     */
    public Filters(String plan) {
        super();
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

}
