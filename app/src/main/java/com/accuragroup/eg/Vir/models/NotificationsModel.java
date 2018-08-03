package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/17/2017.
 */

public class NotificationsModel {

    private String notification;
    private String notDate;
    private String notifyType;

    public NotificationsModel(String notification, String notDate,String notifyType) {
        this.notification = notification;
        this.notDate = notDate;
        this.notifyType=notifyType;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void setNotDate(String notDate) {
        this.notDate = notDate;
    }

    public String getNotification() {
        return notification;
    }

    public String getNotDate() {
        return notDate;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }
}
