
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopInfoResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private ShopInfo _return;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -1486130210382975468L;

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

    public ShopInfo getReturn() {
        return _return;
    }

    public void setReturn(ShopInfo _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
