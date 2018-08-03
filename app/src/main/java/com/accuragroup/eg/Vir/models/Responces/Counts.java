
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts implements Serializable
{

    @SerializedName("countStore")
    @Expose
    private String countStore;
    @SerializedName("countMessage")
    @Expose
    private int countMessage;
    @SerializedName("countOffers")
    @Expose
    private String countOffers;
    @SerializedName("countNotification")
    @Expose
    private String countNotification;
    private final static long serialVersionUID = 4048643725324780909L;

    public String getCountStore() {
        return countStore;
    }

    public void setCountStore(String countStore) {
        this.countStore = countStore;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public String getCountOffers() {
        return countOffers;
    }

    public void setCountOffers(String countOffers) {
        this.countOffers = countOffers;
    }

    public String getCountNotification() {
        return countNotification;
    }

    public void setCountNotification(String countNotification) {
        this.countNotification = countNotification;
    }

}
