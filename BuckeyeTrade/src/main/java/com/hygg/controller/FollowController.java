package com.hygg.controller;

import com.hygg.service.FollowService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FollowController {
    @Reference(version = "0.1")
    private FollowService followService;
    @GetMapping("/follow")
    public Map<String,Object> follow(@RequestParam(value="followerId") int followerId,
                                     @RequestParam(value="followedUserId") int followedUserId){
        return followService.follow(followerId,followedUserId);
    }
    @GetMapping("/un-follow")
    public Map<String,Object> unFollow(@RequestParam(value="followerId") int followerId,
                                       @RequestParam(value="followedUserId") int followedUserId){
        return followService.unFollow(followerId,followedUserId);
    }
    @GetMapping("follower-vos")
    public Map<String,Object> getFollowerVOs(@RequestParam(value="pageNum") int pageNum,
                                             @RequestParam(value="pageSize") int pageSize,
                                             @RequestParam(value="userId") int userId){
        return followService.getFollowerVOs(pageNum,pageSize,userId);
    }
    @GetMapping("following-vos")
    public Map<String,Object> getFollowingVOs(@RequestParam(value="pageNum") int pageNum,
                                              @RequestParam(value="pageSize") int pageSize,
                                              @RequestParam(value="userId") int userId){
        return followService.getFollowingVOs(pageNum,pageSize,userId);
    }
    @GetMapping("/follow-flag")
    public boolean followFlag(@RequestParam(value="followerId") int followerId,
                              @RequestParam(value="followedUserId") int followedUserId){
        return followService.followFlag(followerId,followedUserId);
    }


}
