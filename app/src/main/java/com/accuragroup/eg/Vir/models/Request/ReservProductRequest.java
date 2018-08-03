package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 12/6/2017.
 */

public class ReservProductRequest extends DefaultRequest {


    @SerializedName("userId")
    @Expose
    private int userId;


    @SerializedName("productId")
    @Expose
    private int productId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {

        return userId;
    }

    public int getProductId() {
        return productId;
    }
}
