package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/18/2017.
 */

public class SubCategoryModel {

    private String subCatName;
    private int catParentId;

    public SubCategoryModel(String subCatName, int catParentId) {
        this.subCatName = subCatName;
        this.catParentId = catParentId;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public void setCatParentId(int catParentId) {
        this.catParentId = catParentId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public int getCatParentId() {
        return catParentId;
    }
}
