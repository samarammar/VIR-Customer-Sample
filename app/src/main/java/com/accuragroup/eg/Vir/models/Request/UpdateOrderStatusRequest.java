package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 12/13/2017.
 */

public class UpdateOrderStatusRequest extends DefaultRequest {
    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("orderId")
    @Expose
    private int orderId;

    @SerializedName("status")
    @Expose
    private String status;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {

        return orderId;
    }

    public String getStatus() {
        return status;
    }
}
