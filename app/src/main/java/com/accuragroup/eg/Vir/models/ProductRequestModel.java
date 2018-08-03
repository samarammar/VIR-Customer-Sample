package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/19/2017.
 */


public class ProductRequestModel {
    private String prodName; //c b
    private String prodStatus; //c b
    private String prodImage; //c b
    private String createdAt; //c b
    private String prodOwner; //c
    private String prodPrice; //c
    private String prodSalePrice; //c
    private String reqUserName; //b
    private String gov; //c
    private String city; //c
    private int requestId;

    public ProductRequestModel(int requestId, String prodName, String prodStatus, String prodImage, String createdAt, String prodPrice, String reqUserName) {

        this.requestId=requestId;
        this.prodName = prodName;
        this.prodStatus = prodStatus;
        this.prodImage = prodImage;
        this.createdAt = createdAt;
        this.prodPrice=prodPrice;
        this.reqUserName=reqUserName;

    }

    public ProductRequestModel(int requestId, String prodName, String prodStatus, String prodImage, String createdAt, String prodOwner, String gov, String city, String prodPrice,String prodSalePrice) {
        this.requestId=requestId;
        this.prodName = prodName;
        this.prodStatus = prodStatus;
        this.prodImage = prodImage;
        this.createdAt = createdAt;
        this.prodOwner = prodOwner;
        this.prodPrice=prodPrice;
        this.gov = gov;
        this.city = city;
        this.prodSalePrice=prodSalePrice;


    }


    public void setProdSalePrice(String prodSalePrice) {
        this.prodSalePrice = prodSalePrice;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setReqUserName(String reqUserName) {
        this.reqUserName = reqUserName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setProdOwner(String prodOwner) {
        this.prodOwner = prodOwner;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public String getProdImage() {
        return prodImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getProdOwner() {
        return prodOwner;
    }

    public String getGov() {
        return gov;
    }

    public String getCity() {
        return city;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public String getReqUserName() {
        return reqUserName;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getProdSalePrice() {
        return prodSalePrice;
    }
}

