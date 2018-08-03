package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Apex on 3/11/2018.
 */

public class FollowsRequest extends DefaultRequest {

    @SerializedName("userId")
    @Expose
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
