package com.hygg.vo;

import java.io.Serializable;
import java.util.List;

public class CommentVO implements Serializable {
    private List<ReplyVO> reply;
    private int commentId;
    private String time;
    private int ownerId;
    private int fromId;
    private String fromName;
    private String fromAvatar;
    private String content;

    public void setReply(List<ReplyVO> reply) {
        this.reply = reply;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReplyVO> getReply() {
        return reply;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getTime() {
        return time;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getFromId() {
        return fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public String getContent() {
        return content;
    }
}
