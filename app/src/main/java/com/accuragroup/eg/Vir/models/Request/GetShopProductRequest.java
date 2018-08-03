package com.accuragroup.eg.Vir.models.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ${sayed} on 11/9/2017.
 */

public class GetShopProductRequest extends DefaultRequest {


    @SerializedName("ownerId")
    @Expose
    private int ownerId;


    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("perPage")
    @Expose
    private int perPage;

    @SerializedName("keyword")
    @Expose
    private String keyword;

    @SerializedName("catId")
    @Expose
    private String catId;

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getOwnerId() {

        return ownerId;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getUserId() {
        return userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getCatId() {
        return catId;
    }
}
