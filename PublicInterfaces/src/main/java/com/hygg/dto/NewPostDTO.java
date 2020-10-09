package com.hygg.dto;

import java.io.Serializable;

public class NewPostDTO implements Serializable {
    private String itemName;
    private String description;
    private String contact;
    private String folderUrl;
    private String imgNames;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public void setImgNames(String imgNames) {
        this.imgNames = imgNames;
    }

    public String getImgNames() {
        return imgNames;
    }

    private Integer authorId;
    private String tag;


    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getTag() {
        return tag;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getContact() {
        return contact;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }

}
