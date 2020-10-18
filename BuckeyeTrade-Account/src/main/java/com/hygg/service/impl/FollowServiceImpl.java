package com.hygg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hygg.dao.FollowMapper;
import com.hygg.dto.FollowDTO;
import com.hygg.dto.NotificationDTO;
import com.hygg.entity.Follow;
import com.hygg.entity.FollowExample;
import com.hygg.entity.User;
import com.hygg.service.FollowService;
import com.hygg.service.NotificationService;
import com.hygg.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import java.util.*;

@Service
public class FollowServiceImpl implements FollowService {

    private Logger logger= LoggerFactory.getLogger(FollowServiceImpl.class);
    @Autowired
    FollowMapper followMapper;
    @Reference(version="0.1")
    UserService userService;
    @Reference(version = "0.1")
    NotificationService notificationService;
    @Override
    public boolean followFlag(int followerId, int followedUserId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowedUserIdEqualTo(followedUserId).andFollowerIdEqualTo(followerId);
        List<Follow> followList=followMapper.selectByExample(followExample);
        return followList.size()==1;
    }
    @Override
    public Map<String, Object> follow(int followerId, int followedUserId) {
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setRecipientId(followedUserId);
        notificationDTO.setSenderId(followerId);
        notificationDTO.setTargetId(followerId);
        notificationDTO.setTargetType(3);
        notificationService.insertNotification(notificationDTO);
        Follow follow=new Follow();
        follow.setCreatedAt(new Date());
        follow.setUpdatedAt(new Date());
        follow.setFollowedUserId(followedUserId);
        follow.setFollowerId(followerId);
        int result=followMapper.insert(follow);
        if(result>0){
            return new HashMap<String,Object>(){
                {
                    put("message","success");
                }
            };
        }else{
            return new HashMap<String,Object>(){
                {
                    put("message","failure");
                }
            };
        }
    }

    @Override
    public Map<String, Object> unFollow(int followerId, int followedUserId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowedUserIdEqualTo(followedUserId).andFollowerIdEqualTo(followerId);
        int result=followMapper.deleteByExample(followExample);
        if(result==1){
            return new HashMap<String,Object>(){
                {
                    put("message","success");
                }
            };
        }else{
            return new HashMap<String,Object>(){
                {
                    put("message","failure");
                }
            };
        }
    }

    @Override
    /*
    {
    name,avatar,collegeOrSchool
    }
    * */
    public Map<String, Object> getFollowerVOs(int pageNum, int pageSize,int userId) {
        logger.info("userId"+userId+"pageNum"+pageNum+"pageSize"+pageSize);
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowedUserIdEqualTo(userId);
        List<FollowDTO> followDTOList=new ArrayList<>();
        Page page= PageHelper.startPage(pageNum,pageSize);
        List<Follow> followList=followMapper.selectByExample(followExample);
        long total=page.getTotal();
        for(Follow follow: followList){
            FollowDTO followDTO=new FollowDTO();
            int followerId=follow.getFollowerId();
            User follower=userService.queryUserById(followerId);
            followDTO.setAvatarUrl(follower.getAvatarUrl());
            followDTO.setCollegeOrSchool(follower.getCollegeOrSchool());
            followDTO.setUserId(followerId);
            followDTO.setUserName(follower.getUserName());
            followDTOList.add(followDTO);
        }
        return new HashMap<String,Object>(){
            {
                put("message","success");
                put("data",followDTOList);
                put("pageCount",Math.ceil(1.0*total/pageSize));
            }
        };
    }

    @Override
    public Map<String, Object> getFollowingVOs(int pageNum,int pageSize,int userId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowerIdEqualTo(userId);
        List<FollowDTO> followDTOList=new ArrayList<>();
        Page page= PageHelper.startPage(pageNum,pageSize);
        List<Follow> followList=followMapper.selectByExample(followExample);
        long total=page.getTotal();
        for(Follow follow: followList){
            FollowDTO followDTO=new FollowDTO();
            int followedUserId=follow.getFollowedUserId();
            User followedUser=userService.queryUserById(followedUserId);
            followDTO.setAvatarUrl(followedUser.getAvatarUrl());
            followDTO.setCollegeOrSchool(followedUser.getCollegeOrSchool());
            followDTO.setUserId(followedUserId);
            followDTO.setUserName(followedUser.getUserName());
            followDTOList.add(followDTO);
        }
        return new HashMap<String,Object>(){
            {
                put("message","success");
                put("data",followDTOList);
                put("pageCount",Math.ceil(1.0*total/pageSize));
            }
        };
    }

    @Override
    public Map<String, Object> getPeopleYouMayKnowVOs(int userId) {

        return null;
    }
}
