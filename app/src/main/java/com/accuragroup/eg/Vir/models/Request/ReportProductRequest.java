package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 12/9/2017.
 */

public class ReportProductRequest extends DefaultRequest {

    @SerializedName("userId")
    @Expose
    private int userId;


    @SerializedName("productId")
    @Expose
    private int productId;

    @SerializedName("reason")
    @Expose
    private String reason;


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUserId() {

        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getReason() {
        return reason;
    }
}
