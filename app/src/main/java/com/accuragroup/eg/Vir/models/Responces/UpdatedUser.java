
package com.accuragroup.eg.Vir.models.Responces;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatedUser implements Serializable
{

    @SerializedName("data")
    @Expose
    private UpdatUser data;
    private final static long serialVersionUID = 1297410824298638021L;

    public UpdatUser getData() {
        return data;
    }

    public void setData(UpdatUser data) {
        this.data = data;
    }

}
