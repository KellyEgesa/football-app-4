
package com.moringaschool.football_app.models.competition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Area {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("ensignUrl")
    @Expose
    private Object ensignUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Area() {
    }

    /**
     * 
     * @param ensignUrl
     * @param countryCode
     * @param name
     * @param id
     */
    public Area(Integer id, String name, String countryCode, Object ensignUrl) {
        super();
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.ensignUrl = ensignUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Object getEnsignUrl() {
        return ensignUrl;
    }

    public void setEnsignUrl(Object ensignUrl) {
        this.ensignUrl = ensignUrl;
    }

}
