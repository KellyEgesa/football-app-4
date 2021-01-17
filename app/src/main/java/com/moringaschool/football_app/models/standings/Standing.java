
package com.moringaschool.football_app.models.standings;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standing {

    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("group")
    @Expose
    private Object group;
    @SerializedName("table")
    @Expose
    private List<Table> table = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Standing() {
    }

    /**
     * 
     * @param stage
     * @param type
     * @param table
     * @param group
     */
    public Standing(String stage, String type, Object group, List<Table> table) {
        super();
        this.stage = stage;
        this.type = type;
        this.group = group;
        this.table = table;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

}
