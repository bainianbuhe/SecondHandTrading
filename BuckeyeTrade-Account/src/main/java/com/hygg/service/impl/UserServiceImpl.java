package com.hygg.service.impl;

import com.hygg.dao.UserDao;
import com.hygg.entity.User;
import com.hygg.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User login(String userName,String userPassword){
        return userDao.queryUserByNameAndPass(userName,userPassword);
    }
    @Override
    public int register(User user){
        return userDao.insertUser(user);
    }

    @Override
    public boolean existUserName(String userName) {
        return userDao.queryUserByName(userName)==null?false:true;
    }

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public Map<String, Object> updateUser(User user) {
        userDao.updateUser(user);
        return new HashMap<String,Object>(){
            {
                put("message","success");
            }
        };
    }
}
