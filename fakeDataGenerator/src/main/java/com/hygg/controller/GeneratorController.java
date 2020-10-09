package com.hygg.controller;

import com.hygg.entity.PostInformation;
import com.hygg.entity.User;
import com.hygg.service.PostInformationService;
import com.hygg.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class GeneratorController   {
    @Reference(version = "0.1")
    private UserService userService;
    @Reference(version="0.1")
    private PostInformationService postService;
    @GetMapping("/generate/user")
    public Map<String,Object> generateUser(){
        for(int i=0;i<10;i++){
            int finalI = i;
            User user=new User();
            user.setUserName("test-user"+(finalI +1));
            user.setAvatarUrl("http://47.90.245.180/static/avatars/fake_data/"+(finalI +1)+".jpeg");
            user.setUserPassword("test-password");
            user.setCollegeOrSchool("Engineering");
            userService.register(user);
        }
        return new HashMap<String,Object>(){
            {
                put("asdad","asdasd");
            }

        };
    }
    @GetMapping("/generate/post")
    public Map<String,Object> generatePost(){
        String[] tags={"Electronic","Daily Use","Books","Sports","Tickets","Others"};
        for(int i=0;i<100;i++){
            PostInformation postInformation=new PostInformation();
            postInformation.setStatus(0);
            postInformation.setPostTime(new Date());
            postInformation.setItemName("test-item"+(i+1));
            postInformation.setImgNames("1.png,2.png");
            postInformation.setContact("test contact"+(i+1));
            int index = (int) (Math.random()* tags.length);
            postInformation.setTag(tags[index]);
            postInformation.setFolderUrl("http://47.90.245.180/static/post-photos/fake_data/"+(i+1)+"/");
            int id=(int)(Math.random()* 10)+1;
            postInformation.setAuthorId(id);
            double price=(double)500*Math.random();
            postInformation.setPrice((int)(price*100)/100.0);
            StringBuilder description=new StringBuilder();
            for(int j=0;j<3;j++){
                description.append("This is a test description for item"+(i+1));
            }
            postInformation.setDescription(description.toString());
            postService.uploadInformation(postInformation);
        }
        return new HashMap<String,Object>(){
            {
                put("asdad","asdasd");
            }

        };
    }
}
