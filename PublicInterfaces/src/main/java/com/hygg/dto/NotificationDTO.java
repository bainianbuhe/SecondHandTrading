package com.hygg.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
    private int targetType;
    private int targetId;
    private int senderId;
    private int recipientId;

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getTargetType() {
        return targetType;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }
}
