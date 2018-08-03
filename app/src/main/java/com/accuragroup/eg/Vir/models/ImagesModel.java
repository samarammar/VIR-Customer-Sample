package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 4/25/2018.
 */

public class ImagesModel {
    public String imageUrl;
    public String imageId;

    public ImagesModel(String imageUrl, String imageId) {
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
