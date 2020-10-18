package com.hygg.dao;

import com.hygg.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao{
    public User queryUserByNameAndPass(@Param("userName") String userName,
                                       @Param("userPassword") String userPassword);
    public Integer insertUser(User user);
    public User queryUserByName(String userName);
    public User queryUserById(Integer id);
    public Integer updateUser(User user);
}