package com.hygg.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value="user")
public class User implements Serializable {
    private Long userId;
    private String userName;
    private String userPassword;
    private String collegeOrSchool;
    private String avatarUrl;

    public void setCollegeOrSchool(String collegeOrSchool) {
        this.collegeOrSchool = collegeOrSchool;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCollegeOrSchool() {
        return collegeOrSchool;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;

    }

}
