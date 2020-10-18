package com.hygg.vo;

import java.io.Serializable;

public class NotificationVO implements Serializable {
    private String content;
    private String checkUrl;
    private String createDate;
    private int notificationId;

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setCreateDate(String createDate) {

        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public String getContent() {
        return content;
    }

    public String getCheckUrl() {
        return checkUrl;
    }
}
