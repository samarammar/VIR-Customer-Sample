package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 8/2/2017.
 */

public class OffersModel {

    private int id;
    private String offerTitle;
    private String offerDesc;
    private String offerImage;
    private String offerStart;
    private String offerEnd;

    public int getId() {
        return id;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public String getOfferStart() {
        return offerStart;
    }

    public String getOfferEnd() {
        return offerEnd;
    }

    public OffersModel(int id, String offerTitle, String offerDesc, String offerImage, String offerStart, String offerEnd) {

        this.id = id;
        this.offerTitle = offerTitle;
        this.offerDesc = offerDesc;
        this.offerImage = offerImage;
        this.offerStart = offerStart;
        this.offerEnd = offerEnd;
    }


}
