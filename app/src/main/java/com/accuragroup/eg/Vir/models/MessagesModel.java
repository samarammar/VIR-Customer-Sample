package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/17/2017.
 */

public class MessagesModel {

    private int read;
    private String senderName;
    private String senderImage;
    private String message;
    private String msgDate;
    private int senderId;

    public MessagesModel(int read, String senderName, String senderImage, String message, String msgDate, int senderId) {
        this.senderName = senderName;
        this.senderImage = senderImage;
        this.message = message;
        this.msgDate = msgDate;
        this.senderId = senderId;
        this.read = read;
    }

    public int getRead() {
        return read;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public String getMessage() {
        return message;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public int getSenderId() {
        return senderId;
    }
}
