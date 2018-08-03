package com.accuragroup.eg.Vir.interfaces;


import com.accuragroup.eg.Vir.models.Responces.ShopsResponse;

/**
 * Created by wisyst on 2/20/2018.
 */

public class ConnectionEvent  {

    ShopsResponse result;
    Exception exception;
    boolean isFirstPage = false;

    public ConnectionEvent(ShopsResponse result, Exception exception, boolean isFirstPage) {
        this.result = result;
        this.exception = exception;
        this.isFirstPage = isFirstPage;
    }


    public void setResult(ShopsResponse result) {
        this.result = result;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public ShopsResponse getResult() {

        return result;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }
}
