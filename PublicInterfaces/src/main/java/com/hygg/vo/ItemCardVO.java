package com.hygg.vo;

import java.io.Serializable;

public class ItemCardVO implements Serializable {
    private String imgUrl;
    private String authorAvatarUrl;
    private String authorName;
    private String price;
    private String date;
    private String itemName;
    private String label;
    private int postId;

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setAuthorAvatarUrl(String authorAvatarUrl) {
        this.authorAvatarUrl = authorAvatarUrl;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getItemName() {
        return itemName;
    }

    public String getLabel() {
        return label;
    }
}