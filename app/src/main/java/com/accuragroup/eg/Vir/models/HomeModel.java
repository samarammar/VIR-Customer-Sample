package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/17/2017.
 */

public class HomeModel {
    private String itemName;
    private String itemImage;
    private String catId;

    public HomeModel(String itemName, String itemImage,String catId) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.catId=catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatId() {
        return catId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }
}
