package com.hygg.vo;

import java.io.Serializable;

public class ReplyVO implements Serializable {
    private int replyId;
    private int ownerId;
    private int fromId;
    private String fromName;
    private String fromAvatar;
    private int toId;
    private String toName;
    private String toAvatar;
    private String  content;
    private String time;

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }



    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReplyId() {
        return replyId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public int getToId() {
        return toId;
    }

    public String getToName() {
        return toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
