package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 8/2/2017.
 */

public class ShopReviewModel {
    int id;
    String name;
    String Description;
    float rate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public float getRate() {
        return rate;
    }

    public ShopReviewModel(int id, String name, String description, float rate) {

        this.id = id;
        this.name = name;
        Description = description;
        this.rate = rate;
    }
}
