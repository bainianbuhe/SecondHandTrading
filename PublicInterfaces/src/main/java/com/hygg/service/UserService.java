package com.hygg.service;

import com.hygg.entity.User;

import java.util.Map;

public interface UserService {
    public User login(String userName, String password);
    public int register(User user);
    public boolean existUserName(String userName);
    public User queryUserById(int id);
    public Map<String,Object> updateUser(User user);
}
