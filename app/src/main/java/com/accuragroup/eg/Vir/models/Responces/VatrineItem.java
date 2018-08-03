
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VatrineItem implements Serializable
{

    @SerializedName("vatrine_type")
    @Expose
    private String vatrineType;
    @SerializedName("vatrine_id")
    @Expose
    private String vatrineId;
    @SerializedName("vatrine_name")
    @Expose
    private String vatrineName;
    @SerializedName("vatrine_image")
    @Expose
    private String vatrineImage;
    @SerializedName("vatrine_details")
    @Expose
    private VatrineDetails vatrineDetails;
    private final static long serialVersionUID = -4215810956668712500L;

    public String getVatrineType() {
        return vatrineType;
    }

    public void setVatrineType(String vatrineType) {
        this.vatrineType = vatrineType;
    }

    public String getVatrineId() {
        return vatrineId;
    }

    public void setVatrineId(String vatrineId) {
        this.vatrineId = vatrineId;
    }

    public String getVatrineName() {
        return vatrineName;
    }

    public void setVatrineName(String vatrineName) {
        this.vatrineName = vatrineName;
    }

    public String getVatrineImage() {
        return vatrineImage;
    }

    public void setVatrineImage(String vatrineImage) {
        this.vatrineImage = vatrineImage;
    }

    public VatrineDetails getVatrineDetails() {
        return vatrineDetails;
    }

    public void setVatrineDetails(VatrineDetails vatrineDetails) {
        this.vatrineDetails = vatrineDetails;
    }

}
