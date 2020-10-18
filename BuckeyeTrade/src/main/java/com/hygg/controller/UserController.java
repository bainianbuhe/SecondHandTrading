package com.hygg.controller;

import com.hygg.entity.User;
import com.hygg.service.AvatarUploadService;
import com.hygg.service.FollowService;
import com.hygg.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private  Logger logger=LoggerFactory.getLogger(UserController.class);
    @Reference(version="0.1")
    private AvatarUploadService avatarUploadService;
    @Reference(version="0.1")
    private UserService userService;
    @PostMapping("/uploadAvatar")
    public Map<String,Object> uploadAvatar(
            @RequestParam(value = "file",required = false)
                    MultipartFile file){
        try{
            String fileName=file.getOriginalFilename();
            return avatarUploadService.upload(file.getBytes(),fileName.substring(fileName.lastIndexOf(".")+1));
        }
        catch (IOException e){
            logger.info("IOexeption");
            e.printStackTrace();
        }
        return new HashMap<String,Object>(){{
            put("result","failure");
            put("message","failed to get bytes");
        }};
    }
    @GetMapping("/login")
    public Map<String,Object> login(@RequestParam(value="password") String passWord, @RequestParam(value="userName") String userName){
        User queryResult= userService.login(userName,passWord);
        if(queryResult==null){
            return new HashMap<String,Object>(){
                {
                    put("result","failure");
                    put("message","no such user");
                }
            };
        }
        else{
            return new HashMap<String,Object>(){{
                put("result","success");
                put("data",queryResult);
            }
            };
        }
    }
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user){
        if(userService.existUserName(user.getUserName())){
            return new HashMap<String,Object>(){
                {
                    put("result","failure");
                    put("message","User name already exists!");
                }
            };
        }
        Integer insertResult=userService.register(user);
        if(insertResult==null){
            return new HashMap<String,Object>(){
                {
                    put("result","failure");
                    put("message","Failed to insert user");
                }
            };
        }
        else{
            return new HashMap<String, Object>(){
                {
                    put("result","success");
                    put("id",insertResult);
                }
            };
        }
    }
    @GetMapping("/query-user-by-id")
    public Map<String,Object> queryUserById(@RequestParam(value = "userId")int userId){
        return new HashMap<String,Object>() {
            {
                put("message", "success");
                put("data", userService.queryUserById(userId));
            }
        };
    }
    @PostMapping("/update-user")
    public Map<String,Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
