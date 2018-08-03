package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/19/2017.
 */

public class TipsModel {

    private String tipsDecription;
    private String tipsImage;
    private String userType;

    public TipsModel(String tipsDecription, String tipsImage, String userType) {
        this.tipsDecription = tipsDecription;
        this.tipsImage = tipsImage;
        this.userType = userType;
    }

    public void setTipsDecription(String tipsDecription) {
        this.tipsDecription = tipsDecription;
    }

    public void setTipsImage(String tipsImage) {
        this.tipsImage = tipsImage;
    }

    public void setUserType(String userType) {
        this.userType = userType;

    }

    public String getTipsDecription() {
        return tipsDecription;
    }

    public String getTipsImage() {
        return tipsImage;
    }

    public String getUserType() {
        return userType;
    }

}
