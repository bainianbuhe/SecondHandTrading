package com.hygg.controller;

import com.hygg.dto.CommentDTO;
import com.hygg.dto.ReplyDTO;
import com.hygg.service.CommentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommentController {
    @Reference(version = "0.1")
    private CommentService commentService;
    @PostMapping("/insert-comment")
    public Map<String,Object> insertComment(@RequestBody CommentDTO commentDTO){
        return commentService.insertComment(commentDTO);
    }
    @PostMapping("/insert-reply")
    public Map<String,Object> insertReply(@RequestBody ReplyDTO replyDTO){
        return commentService.insertReply(replyDTO);
    }
    @GetMapping("/get-comment-board")
    public Map<String,Object> getCommentBoard(@RequestParam(value = "postId") int postId){
        return commentService.getCommentBoardVO(postId);
    }

}
