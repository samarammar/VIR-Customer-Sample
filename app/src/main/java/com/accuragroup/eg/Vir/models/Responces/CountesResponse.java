
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountesResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private Counts _return;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -4664889835674161907L;

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

    public Counts getReturn() {
        return _return;
    }

    public void setReturn(Counts _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
