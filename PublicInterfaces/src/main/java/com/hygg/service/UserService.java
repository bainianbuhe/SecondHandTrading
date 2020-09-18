package com.hygg.service;

import com.hygg.entity.User;

public interface UserService {
    public User login(String userName, String password);
    public int register(User user);
    public boolean existUserName(String userName);
}
