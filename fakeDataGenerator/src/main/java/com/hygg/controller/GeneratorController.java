package com.hygg.controller;

import com.hygg.dto.CommentDTO;
import com.hygg.dto.ReplyDTO;
import com.hygg.entity.PostInformation;
import com.hygg.entity.User;
import com.hygg.service.CommentService;
import com.hygg.service.FollowService;
import com.hygg.service.PostInformationService;
import com.hygg.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class GeneratorController   {
    @Reference(version = "0.1")
    private UserService userService;
    @Reference(version="0.1")
    private PostInformationService postService;
    @Reference(version="0.1")
    private CommentService commentService;
    @Reference(version = "0.1")
    private FollowService followService;
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
    @GetMapping("/generate/comments")
    public Map<String,Object> generateComments() throws InterruptedException {
        HashSet<Integer> allUserId=new HashSet<>();
        int count=0;
        int i=101;
            HashSet<Integer> commentUserId=new HashSet<>();
            while(commentUserId.size()<4){
                commentUserId.add((int)(10*Math.random())+1);
            }
            ArrayList<Integer> commentUserIdList=new ArrayList<>();
            commentUserIdList.addAll(commentUserId);
            for(int j=0;j<4;j++){
                count++;
                CommentDTO commentDTO=new CommentDTO();
                commentDTO.setContent("test comment from test-user"+commentUserIdList.get(j));
                commentDTO.setFromId(commentUserIdList.get(j));
                commentDTO.setOwnerId(i+1);
                //commentId should be  the owner id of reply
                int tmpId=commentUserIdList.get(j);
                for(int k=0;k<3;k++){
                    ReplyDTO replyDTO=new ReplyDTO();
                    int fromId=(int)(10*Math.random())+1;
                    replyDTO.setFromId(fromId);
                    replyDTO.setContent("test reply from test-user"+fromId);
                    replyDTO.setOwnerId(count);
                    replyDTO.setToId(tmpId);
                    tmpId=fromId;
                    commentService.insertReply(replyDTO);
                    TimeUnit.SECONDS.sleep(1);//秒
                }
                commentService.insertComment(commentDTO);
            }
        return new HashMap<String,Object>(){
            {
                put("sada","asdasd");
            }
        };
    }
    @GetMapping("/generate/follows")
    public Map<String,Object> generateFollows()  {
       int[] followerIdList={1,2,4,5,6,7,8,9,10};
       for(int followerId : followerIdList){
           followService.follow(followerId,3);
       }
        return new HashMap<String,Object>(){
            {
                put("sada","asdasd");
            }
        };
    }
}
