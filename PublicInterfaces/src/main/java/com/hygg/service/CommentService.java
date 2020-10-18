package com.hygg.service;

import com.hygg.dto.CommentDTO;
import com.hygg.dto.ReplyDTO;
import com.hygg.entity.Comment;
import com.hygg.entity.Reply;

import java.util.Map;

public interface CommentService{
    public Map<String, Object> insertComment(CommentDTO commentDTO);
    public Map<String,Object> insertReply(ReplyDTO replyDTO);
    public Map<String,Object> getCommentBoardVO(int postId);
    public Reply getReply(int replyId);
    public Comment getComment(int commentId);
}
