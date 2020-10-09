package com.hygg.vo;

import java.io.Serializable;

public class ItemDetailVO implements Serializable {
    private String[] imgUrls;
    private String authorAvatarUrl;
    private String authorName;
    private String price;
    private String itemName;
    private String tag;
    private String contact;
    private String description;
    private String postTime;

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
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


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String[] getImgUrls() {
        return imgUrls;
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


    public String getItemName() {
        return itemName;
    }

    public String getTag() {
        return tag;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }



    public String getPostTime() {
        return postTime;
    }
}
