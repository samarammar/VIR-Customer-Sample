
package com.accuragroup.eg.Vir.models.Responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VatrineDetails implements Serializable {

    @SerializedName("product_parent_category")
    @Expose
    private String productParentCategory;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("product_owner")
    @Expose
    private String productOwner;
    @SerializedName("products_delivery")
    @Expose
    private String productsDelivery;
    @SerializedName("can_reserve")
    @Expose
    private int canReserve;
    @SerializedName("product_description")
    @Expose
    private String productDescription;

    @SerializedName("gov")
    @Expose
    private String gov;


    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("offer_to_date")
    @Expose
    private String offer_to_date;

    @SerializedName("offer_from_date")
    @Expose
    private String offer_from_date;

    @SerializedName("offer_description")
    @Expose
    private String offer_description;

    @SerializedName("offer_image")
    @Expose
    private String offer_image;

    @SerializedName("offer_title")
    @Expose
    private String offer_title;




    @SerializedName("zone")
    @Expose
    private String zone;


    @SerializedName("product_rate")
    @Expose
    private int productRate;
    @SerializedName("product_reviews")
    @Expose
    private int productReviews;
    @SerializedName("product_likes")
    @Expose
    private int productLikes;
    @SerializedName("reserved")
    @Expose
    private int reserved;
    @SerializedName("favorites")
    @Expose
    private int favorites;
    private final static long serialVersionUID = -1870432945577030872L;

    public String getProductParentCategory() {
        return productParentCategory;
    }

    public void setProductParentCategory(String productParentCategory) {
        this.productParentCategory = productParentCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


    public void setOffer_to_date(String offer_to_date) {
        this.offer_to_date = offer_to_date;
    }

    public void setOffer_from_date(String offer_from_date) {
        this.offer_from_date = offer_from_date;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }

    public void setOffer_image(String offer_image) {
        this.offer_image = offer_image;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getGov() {

        return gov;
    }

    public String getCity() {
        return city;
    }

    public String getZone() {
        return zone;
    }


    public String getOffer_to_date() {
        return offer_to_date;
    }

    public String getOffer_from_date() {
        return offer_from_date;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductsDelivery() {
        return productsDelivery;
    }

    public void setProductsDelivery(String productsDelivery) {
        this.productsDelivery = productsDelivery;
    }

    public int getCanReserve() {
        return canReserve;
    }

    public void setCanReserve(int canReserve) {
        this.canReserve = canReserve;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductRate() {
        return productRate;
    }

    public void setProductRate(int productRate) {
        this.productRate = productRate;
    }

    public int getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(int productReviews) {
        this.productReviews = productReviews;
    }

    public int getProductLikes() {
        return productLikes;
    }

    public void setProductLikes(int productLikes) {
        this.productLikes = productLikes;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

}
