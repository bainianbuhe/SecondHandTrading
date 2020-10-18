package com.hygg.dto;

import java.io.Serializable;

public class FollowDTO implements Serializable {
    private String avatarUrl;
    private String userName;
    private String collegeOrSchool;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    private int userId;


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCollegeOrSchool(String collegeOrSchool) {
        this.collegeOrSchool = collegeOrSchool;
    }

    public String getCollegeOrSchool() {
        return collegeOrSchool;
    }
}
