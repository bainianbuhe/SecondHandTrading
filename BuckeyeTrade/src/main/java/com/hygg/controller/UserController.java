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
        HashMap<String,Object> result=new HashMap<>();
        result.put("result","failure");
        result.put("message","failed to get bytes");
        return result;
    }
    @GetMapping("/login")
    public Map<String,Object> login(@RequestParam(value="password") String passWord, @RequestParam(value="userName") String userName){
        User queryResult= userService.login(userName,passWord);
        if(queryResult==null){
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","failure");
            result.put("message","no such user");
            return result;
        }
        else{
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","success");
            result.put("data",queryResult);
            return result;
        }
    }
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user){
        if(userService.existUserName(user.getUserName())){
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","failure");
            result.put("message","User name already exists!");
            return result;
        }
        Integer insertResult=userService.register(user);
        if(insertResult==null){
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","failure");
            result.put("message","Failed to insert user");
            return result;
        }
        else{
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","success");
            result.put("id",insertResult);
            return result;
        }
    }
    @GetMapping("/query-user-by-id")
    public Map<String,Object> queryUserById(@RequestParam(value = "userId")int userId){
        HashMap<String,Object> result=new HashMap<>();
        result.put("message", "success");
        result.put("data", userService.queryUserById(userId));
        return result;
    }
    @PostMapping("/update-user")
    public Map<String,Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
