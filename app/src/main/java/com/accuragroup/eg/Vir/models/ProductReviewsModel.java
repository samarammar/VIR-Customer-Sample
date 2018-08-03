package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/24/2017.
 */

public class ProductReviewsModel {

    private String reviewContent;
    private String reviewRate;
    private String reviewUser;



    public ProductReviewsModel(String reviewContent, String reviewRate, String reviewUser) {
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.reviewUser = reviewUser;

    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }


    public void setReviewRate(String reviewRate) {
        this.reviewRate = reviewRate;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getReviewContent() {
        return reviewContent;
    }


    public String getReviewRate() {
        return reviewRate;
    }

    public String getReviewUser() {
        return reviewUser;
    }
}
