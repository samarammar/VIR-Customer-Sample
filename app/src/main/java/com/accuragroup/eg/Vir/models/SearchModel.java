package com.accuragroup.eg.Vir.models;

import java.util.List;

/**
 * Created by Apex on 7/20/2017.
 */

public class SearchModel {
    private int shopID;
    private String shopName;
    private String shopImage;
    private String shopeCity;
    private String shopeGovern;
    private float shopeRate;
    private List<String> shopCategoryList;


    public String getShopName() {
        return shopName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public String getShopeCity() {
        return shopeCity;
    }

    public String getShopeGovern() {
        return shopeGovern;
    }

    public float getShopeRate() {
        return shopeRate;
    }

    public List<String> getShopCategoryList() {
        return shopCategoryList;
    }

    public SearchModel(int shopID, String shopName, String shopImage, String shopeCity, String shopeGovern, float shopeRate, List<String> shopCategoryList) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopImage = shopImage;
        this.shopeCity = shopeCity;
        this.shopeGovern = shopeGovern;
        this.shopeRate = shopeRate;
        this.shopCategoryList = shopCategoryList;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
}
