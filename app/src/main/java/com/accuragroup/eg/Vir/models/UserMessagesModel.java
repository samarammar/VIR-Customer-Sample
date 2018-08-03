package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/25/2017.
 */

public class UserMessagesModel {
    private String ID;
    private String msgText;
    private String senderImage;
    private int senderId;
    private String senderName;
    private String msgDate;

    public UserMessagesModel(String ID, String msgText, String senderImage, int senderId, String senderName, String msgDate) {
        this.ID = ID;
        this.msgText = msgText;
        this.senderImage = senderImage;
        this.senderId = senderId;
        this.senderName = senderName;
        this.msgDate = msgDate;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getID() {
        return ID;
    }

    public String getMsgText() {
        return msgText;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMsgDate() {
        return msgDate;
    }
}
