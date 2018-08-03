package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 8/1/2017.
 */

public class ShopProductsModel {


    private int iD;
    private String productName;
    private String productImage;
    private int price;
    private String productCategory;
    private int favorit;

    public int getCanReserv() {
        return canReserv;
    }

    private int canReserv;

    public void setReseved(int reseved) {
        this.reseved = reseved;
    }

    private int reseved;

    public void setFavorit(int favorit) {
        this.favorit = favorit;
    }


    public int getReseved() {
        return reseved;
    }

    public int getFavorit() {
        return favorit;
    }

    public int getiD() {
        return iD;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getPrice() {
        return price;
    }

    public String getProductCategory() {
        return productCategory;
    }


    public ShopProductsModel(String productName, int iD, String productImage, int price, String productCategory, int fav, int rec, int CanReserv) {
        this.productName = productName;
        this.iD = iD;
        this.productImage = productImage;
        this.price = price;
        this.productCategory = productCategory;
        this.favorit = fav;
        this.canReserv = CanReserv;
        this.reseved = rec;
    }


}
