package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 11/6/2017.
 */

public class ZoneRequest extends DefaultRequest {

    @SerializedName("govId")
    @Expose
    private int govId;


    @SerializedName("cityId")
    @Expose
    private int cityId;


    public void setGovId(int govId) {
        this.govId = govId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getGovId() {

        return govId;
    }

    public int getCityId() {
        return cityId;
    }
}
