package com.hygg.dto;

import java.io.Serializable;

public class NewPostDTO implements Serializable {
    private String itemName;
    private String description;
    private String contact;
    private String folderUrl;
    private int imgNum;

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

    public int getImgNum() {
        return imgNum;
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

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }
}
