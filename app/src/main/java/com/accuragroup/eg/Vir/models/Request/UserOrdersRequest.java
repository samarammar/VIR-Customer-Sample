package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 10/16/2017.
 */

public class UserOrdersRequest extends DefaultRequest {

    @SerializedName("userId")
    @Expose
    private int userID;



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
