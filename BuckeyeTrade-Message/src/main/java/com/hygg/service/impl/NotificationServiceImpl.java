package com.hygg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hygg.dao.NotificationMapper;
import com.hygg.dto.NotificationDTO;
import com.hygg.entity.*;
import com.hygg.service.CommentService;
import com.hygg.service.NotificationService;
import com.hygg.service.PostInformationService;
import com.hygg.service.UserService;
import com.hygg.vo.NotificationVO;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotificationServiceImpl implements NotificationService {
    Logger logger= LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    private NotificationMapper notificationMapper;
    @Reference(version="0.1")
    private UserService userService;
    @Reference(version = "0.1")
    private PostInformationService postInformationService;
    @Reference(version = "0.1")
    private CommentService commentService;
    //target_type(comment1/reply2/follow3)
    //target_id(post_id/reply_id/user_id)
    @Override
    public Map<String, Object> insertNotification(NotificationDTO notificationDTO) {
        Notification notification=new Notification();
        notification.setCreateDate(new Date());
        notification.setRecipientId(notificationDTO.getRecipientId());
        notification.setSenderId(notificationDTO.getSenderId());
        notification.setStatus(0);
        notification.setTargetId(notificationDTO.getTargetId());
        notification.setTargetType(notificationDTO.getTargetType());
        logger.info("inserting notification from"+notificationDTO.getSenderId()+"to"+notificationDTO.getRecipientId());
        if(notification.getRecipientId()!=notification.getSenderId()){
            int result=notificationMapper.insert(notification);
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
        else{
            return null;
        }
    }

    @Override
    public Map<String, Object> markAsRead(int notificationId) {
        notificationMapper.markAsRead(notificationId);
        return new HashMap<String,Object>(){
            {
                put("message","success");
            }
        };
    }

    @Override
    public Map<String, Object> listReadNotificationVOs(int pageNum, int pageSize, int recipientId) {
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andRecipientIdEqualTo(recipientId).andStatusEqualTo(1);
        notificationExample.setOrderByClause("create_date desc");
        Page page= PageHelper.startPage(pageNum,pageSize);
        List<Notification> notificationList=notificationMapper.selectByExample(notificationExample);
        List<NotificationVO> notificationVOList=new ArrayList<>();
        long total=page.getTotal();
        for(Notification notification:notificationList){
            //target_type(comment1/reply/follow3)
            //target_id(post_id/reply_id/user_id)
            int type=notification.getTargetType();
            if(type==1){
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                PostInformation postInformation=postInformationService.getPostInformation(notification.getTargetId());
                String content=senderName+"left a comment under your post"+postInformation.getItemName();
                NotificationVO notificationVO=new NotificationVO();
                notificationVO.setContent(content);
                notificationVO.setNotificationId(notification.getNotificationId());
                notificationVO.setCheckUrl("/item-detail/"+postInformation.getPostId());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVOList.add(notificationVO);
            }
            if(type==2){
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                Reply reply=commentService.getReply(notification.getTargetId());
                int commentId=reply.getOwnerId();
                Comment comment=commentService.getComment(commentId);
                int postId=comment.getOwnerId();
                PostInformation postInformation=postInformationService.getPostInformation(postId);
                NotificationVO notificationVO=new NotificationVO();
                notificationVO.setNotificationId(notification.getNotificationId());
                String content=senderName+"relied to you under post"+postInformation.getItemName();
                notificationVO.setContent(content);
                notificationVO.setCheckUrl("/item-detail/"+postInformation.getPostId());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVOList.add(notificationVO);
            }
            if(type==3){
                NotificationVO notificationVO=new NotificationVO();
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                String content=senderName+"became your follower";
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVO.setNotificationId(notification.getNotificationId());
                notificationVO.setContent(content);
                notificationVO.setCheckUrl("/profile/following");
                notificationVOList.add(notificationVO);
            }
        }
        return new HashMap<String,Object>(){
            {
                put("pageCount",Math.ceil(1.0*total/pageSize));
                put("data",notificationVOList);
            }
        };
    }

    @Override
    public Map<String, Object> listUnreadNotificationVOs(int pageNum, int pageSize, int recipientId) {
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andRecipientIdEqualTo(recipientId).andStatusEqualTo(0);
        notificationExample.setOrderByClause("create_date desc");
        Page page= PageHelper.startPage(pageNum,pageSize);
        List<Notification> notificationList=notificationMapper.selectByExample(notificationExample);
        List<NotificationVO> notificationVOList=new ArrayList<>();
        long total=page.getTotal();
        for(Notification notification:notificationList){
            //target_type(comment1/reply/follow3)
            //target_id(post_id/reply_id/user_id)
            int type=notification.getTargetType();
            if(type==1){
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                PostInformation postInformation=postInformationService.getPostInformation(notification.getTargetId());
                String content=senderName+" left a comment under your post "+postInformation.getItemName();
                NotificationVO notificationVO=new NotificationVO();
                notificationVO.setContent(content);
                notificationVO.setCheckUrl("/item-detail/"+postInformation.getPostId());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVO.setNotificationId(notification.getNotificationId());
                notificationVOList.add(notificationVO);
            }
            if(type==2){
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                Reply reply=commentService.getReply(notification.getTargetId());
                int commentId=reply.getOwnerId();
                Comment comment=commentService.getComment(commentId);
                int postId=comment.getOwnerId();
                PostInformation postInformation=postInformationService.getPostInformation(postId);
                NotificationVO notificationVO=new NotificationVO();
                String content=senderName+" replied to you under post "+postInformation.getItemName();
                notificationVO.setContent(content);
                notificationVO.setCheckUrl("/item-detail/"+postInformation.getPostId());
                notificationVO.setNotificationId(notification.getNotificationId());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVOList.add(notificationVO);
            }
            if(type==3){
                NotificationVO notificationVO=new NotificationVO();
                String senderName=userService.queryUserById(notification.getSenderId()).getUserName();
                String content=senderName+" became your follower";
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
                notificationVO.setNotificationId(notification.getNotificationId());
                String dateTime=simpleDateFormat.format(notification.getCreateDate());
                notificationVO.setCreateDate(dateTime);
                notificationVO.setContent(content);
                notificationVO.setCheckUrl("/profile/following");
                notificationVOList.add(notificationVO);
            }
        }
        return new HashMap<String,Object>(){
            {
                put("total",total);
                put("pageCount",Math.ceil(1.0*total/pageSize));
                put("data",notificationVOList);
            }
        };
    }


}
