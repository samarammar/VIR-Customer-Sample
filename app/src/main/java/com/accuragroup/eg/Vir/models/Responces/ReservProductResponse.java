
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservProductResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private int _return;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("reservation_days")
    @Expose
    private String reservation_days;
    private final static long serialVersionUID = 583637611394071552L;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public int getReturn() {
        return _return;
    }

    public void setReturn(int _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setReservation_days(String reservation_days) {
        this.reservation_days = reservation_days;
    }

    public String getReservation_days() {
        return reservation_days;
    }
}
