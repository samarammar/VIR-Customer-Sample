package com.accuragroup.eg.Vir.models.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by ${sayed} on 12/5/2017.
 */

@Entity
public class FavouritProduct implements Serializable {

    @Id
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("owner")
    @Expose
    private String owner;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {

        return owner;
    }

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("city")
    @Expose
    private String city;


    @SerializedName("gov")
    @Expose
    private String gov;


    @SerializedName("cat")
    @Expose
    private String cat;

    private static final long serialVersionUID = 1L;//or some long

    @Generated(hash = 150340851)
    public FavouritProduct(long id, String img, String title, String owner,
                           int price, String city, String gov, String cat) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.owner = owner;
        this.price = price;
        this.city = city;
        this.gov = gov;
        this.cat = cat;
    }

    @Generated(hash = 239567927)
    public FavouritProduct() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGov() {
        return this.gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getCat() {
        return this.cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }


}
