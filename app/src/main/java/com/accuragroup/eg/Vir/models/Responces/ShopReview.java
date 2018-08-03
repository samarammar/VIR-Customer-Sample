
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopReview implements Serializable
{

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("review_text")
    @Expose
    private String reviewText;
    @SerializedName("rate_value")
    @Expose
    private String rateValue;
    private final static long serialVersionUID = -5001198782736822675L;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

}
