package com.hygg.dto;

import java.io.Serializable;

public class CommentDTO implements Serializable {
    private int fromId;
    private String content;
    private int ownerId;
    public int getFromId() {
        return fromId;
    }

    public String getContent() {
        return content;
    }


    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
