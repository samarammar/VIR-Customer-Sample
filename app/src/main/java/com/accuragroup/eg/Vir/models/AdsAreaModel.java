package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 9/19/2017.
 */

public class AdsAreaModel {
    private int adsId;
    private String adsName;

    public AdsAreaModel(int adsId, String adsName) {
        this.adsId = adsId;
        this.adsName = adsName;
    }

    public void setAdsId(int adsId) {
        this.adsId = adsId;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }

    public int getAdsId() {
        return adsId;
    }

    public String getAdsName() {
        return adsName;
    }
    @Override
    public String toString() {
        return adsName;
    }
}
