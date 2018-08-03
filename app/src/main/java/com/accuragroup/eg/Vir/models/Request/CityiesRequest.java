package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class CityiesRequest extends DefaultRequest {


    @SerializedName("govId")
    @Expose
    private int govId;

    public void setGovId(int govId) {
        this.govId = govId;
    }
}
