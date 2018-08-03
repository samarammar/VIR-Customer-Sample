
package com.accuragroup.eg.Vir.models.Responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShopInfo implements Serializable {

    @SerializedName("gov")
    @Expose
    private String gov;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("products_delivery")
    @Expose
    private String productsDelivery;
    @SerializedName("facebook_link")
    @Expose
    private String facebookLink;
    @SerializedName("website_link")
    @Expose
    private String websiteLink;
    @SerializedName("shop_services")
    @Expose
    private List<String> shopServices = null;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("location")
    @Expose
    private String location;
    private final static long serialVersionUID = -8703983301555168203L;

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getProductsDelivery() {
        return productsDelivery;
    }

    public void setProductsDelivery(String productsDelivery) {
        this.productsDelivery = productsDelivery;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public List<String> getShopServices() {
        return shopServices;
    }

    public void setShopServices(List<String> shopServices) {
        this.shopServices = shopServices;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
