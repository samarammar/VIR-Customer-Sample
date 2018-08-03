package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class VatrinaRequest extends DefaultRequest {

    @SerializedName("ownerId")
    @Expose
    private int ownerId;

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {

        return ownerId;
    }
}


