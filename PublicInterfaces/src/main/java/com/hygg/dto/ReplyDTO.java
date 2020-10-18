package com.hygg.dto;

import java.io.Serializable;

public class ReplyDTO implements Serializable {
    private int fromId;
    private int toId;
    private int ownerId;
    private String content;

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getContent() {
        return content;
    }
}
