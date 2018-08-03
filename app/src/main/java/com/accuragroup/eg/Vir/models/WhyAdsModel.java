package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/18/2017.
 */

public class WhyAdsModel {

    private String adTitle;
    private String adImage;
    private String adDesc;

    public WhyAdsModel(String adTitle, String adImage , String adDesc) {
        this.adTitle = adTitle;
        this.adImage = adImage;
        this.adDesc=adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdImage() {
        return adImage;
    }

    public String getAdDesc() {
        return adDesc;
    }
}
