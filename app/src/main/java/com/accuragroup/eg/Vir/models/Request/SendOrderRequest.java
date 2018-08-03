package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 10/4/2017.
 */

public class SendOrderRequest extends DefaultRequest {

    @SerializedName("serviceId_Quantity")
    @Expose
    private String serviceId_Quantity;
    @SerializedName("userLocation")
    @Expose
    private String userLocation;
    @SerializedName("totalCost")
    @Expose
    private String totalCost;
    @SerializedName("userId")
    @Expose
    private int userId;


    public void setServiceId_Quantity(String serviceId_Quantity) {
        this.serviceId_Quantity = serviceId_Quantity;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getServiceId_Quantity() {
        return serviceId_Quantity;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public int getUserId() {
        return userId;
    }
}
