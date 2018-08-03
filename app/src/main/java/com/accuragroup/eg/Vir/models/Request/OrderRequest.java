package com.accuragroup.eg.Vir.models.Request;

import com.accuragroup.eg.Vir.models.Entities.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ${sayed} on 10/4/2017.
 */

public class OrderRequest extends DefaultRequest {


    @SerializedName("serviceId_Quantity")
    @Expose
    private List<Item> items = null;
    @SerializedName("userId")
    @Expose
    private int userID;
    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("userLocation")
    @Expose
    private String location;

    @SerializedName("totalCost")
    @Expose
    private int total;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Item> getItems() {

        return items;
    }

    public int getUserID() {
        return userID;
    }

    public String getNotes() {
        return notes;
    }

    public String getLocation() {
        return location;
    }

    public int getTotal() {
        return total;
    }
}
