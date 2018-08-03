package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 12/5/2017.
 */

public class CompleteDataRequest extends DefaultRequest {


    @SerializedName("userType")
    @Expose
    private String userType;


    @SerializedName("id")
    @Expose
    private int id;


    @SerializedName("phone")
    @Expose
    private String phone;


    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("B_date")
    @Expose
    private String B_date;


    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setB_date(String b_date) {
        B_date = b_date;
    }

    public String getUserType() {

        return userType;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getB_date() {
        return B_date;
    }
}
