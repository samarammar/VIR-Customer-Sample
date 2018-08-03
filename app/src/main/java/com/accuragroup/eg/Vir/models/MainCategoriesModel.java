package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/17/2017.
 */

public class MainCategoriesModel {

    private String catName;
    private String catIcon;
    private String catId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }


    public MainCategoriesModel(String catName, String catIcon, String catId) {
        this.catName = catName;
        this.catIcon = catIcon;
        this.catId = catId;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }


    public String getCatName() {
        return catName;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }

    public String getCatIcon() {

        return catIcon;
    }
}
