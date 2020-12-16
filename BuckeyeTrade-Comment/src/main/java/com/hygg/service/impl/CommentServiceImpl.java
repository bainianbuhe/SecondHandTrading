package com.hygg.service.impl;

import com.hygg.dao.CommentMapper;
import com.hygg.dao.ReplyMapper;
import com.hygg.dto.CommentDTO;
import com.hygg.dto.NotificationDTO;
import com.hygg.dto.ReplyDTO;
import com.hygg.entity.*;
import com.hygg.service.CommentService;
import com.hygg.service.NotificationService;
import com.hygg.service.PostInformationService;
import com.hygg.service.UserService;
import com.hygg.vo.CommentVO;
import com.hygg.vo.ReplyVO;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    Logger logger= LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Reference(version="0.1")
    private UserService userService;
    @Reference(version="0.1")
    private NotificationService notificationService;
    @Reference(version="0.1")
    private PostInformationService postInformationService;
    @Override
    public Map<String, Object> insertComment(CommentDTO commentDTO) {
        Comment comment=new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCommentTime(new Date());
        comment.setFromId(commentDTO.getFromId());
        comment.setOwnerId(commentDTO.getOwnerId());
        logger.info("ownerID"+commentDTO.getOwnerId());
        commentMapper.insert(comment);
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setTargetType(1);
        notificationDTO.setTargetId(commentDTO.getOwnerId());
        notificationDTO.setSenderId(commentDTO.getFromId());
        PostInformation postInformation=postInformationService.getPostInformation(commentDTO.getOwnerId());
        notificationDTO.setRecipientId(postInformation.getAuthorId());
        notificationService.insertNotification(notificationDTO);
        Map<String, Object> result=new HashMap<>();
        result.put("message","success");
        return result;
    }

    @Override
    public Map<String, Object> insertReply(ReplyDTO replyDTO) {
        logger.info("inserting reply from"+replyDTO.getFromId()+"to"+replyDTO.getToId());
        Reply reply=new Reply();
        reply.setContent(replyDTO.getContent());
        reply.setFromId(replyDTO.getFromId());
        reply.setOwnerId(replyDTO.getOwnerId());
        reply.setReplyTime(new Date());
        reply.setToId(replyDTO.getToId());
        int replyId=replyMapper.insert(reply);
        NotificationDTO notificationDTO=new NotificationDTO();
        notificationDTO.setTargetType(2);
        notificationDTO.setTargetId(replyId);
        notificationDTO.setSenderId(reply.getFromId());
        notificationDTO.setRecipientId(reply.getToId());
        notificationService.insertNotification(notificationDTO);
        Map<String, Object> result=new HashMap<>();
        result.put("message","success");
        return result;
    }

    @Override
    public Map<String, Object> getCommentBoardVO(int postId) {
       List<CommentVO> commentVOList=new ArrayList<>();
       CommentExample commentExample=new CommentExample();
       commentExample.setOrderByClause("comment_time desc");
       commentExample.createCriteria().andOwnerIdEqualTo(postId);
       List<Comment> commentList=commentMapper.selectByExample(commentExample);
        for(Comment comment:commentList){
            CommentVO commentVO=new CommentVO();
            commentVO.setCommentId(comment.getCommentId());
            commentVO.setContent(comment.getContent());
            int fromId=comment.getFromId();
            User fromUser=userService.queryUserById(fromId);
            commentVO.setFromId(fromId);
            commentVO.setFromAvatar(fromUser.getAvatarUrl());
            commentVO.setFromName(fromUser.getUserName());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            commentVO.setTime(simpleDateFormat.format(comment.getCommentTime()));
            commentVO.setOwnerId(postId);
            List<ReplyVO> replyVOList=new ArrayList<>();
            ReplyExample replyExample=new ReplyExample();
            replyExample.setOrderByClause("reply_time desc");
            replyExample.createCriteria().andOwnerIdEqualTo(comment.getCommentId());
            List<Reply> replyList=replyMapper.selectByExample(replyExample);
            for(Reply reply:replyList){
                ReplyVO replyVO=new ReplyVO();
                replyVO.setContent(reply.getContent());
                int fromIdInReply=reply.getFromId();
                User userFrom=userService.queryUserById(fromIdInReply);
                replyVO.setFromId(fromIdInReply);
                replyVO.setFromAvatar(userFrom.getAvatarUrl());
                replyVO.setFromName(userFrom.getUserName());
                replyVO.setOwnerId(comment.getCommentId());
                replyVO.setReplyId(reply.getReplyId());
                replyVO.setTime(simpleDateFormat.format(reply.getReplyTime()));
                int toIdInReply=reply.getToId();
                User userTo= userService.queryUserById(toIdInReply);
                replyVO.setToAvatar(userTo.getAvatarUrl());
                replyVO.setToId(toIdInReply);
                replyVO.setToName(userTo.getUserName());
                replyVOList.add(replyVO);
            }
            commentVO.setReply(replyVOList);
            commentVOList.add(commentVO);
        }
        Map<String, Object> result=new HashMap<>();
        result.put("message","success");
        result.put("data",commentVOList);
        return result;
    }
    @Override
    public Reply getReply(int replyId){
        return replyMapper.selectByPrimaryKey(replyId);
    }

    @Override
    public Comment getComment(int commentId) {
        return  commentMapper.selectByPrimaryKey(commentId);
    }
}
