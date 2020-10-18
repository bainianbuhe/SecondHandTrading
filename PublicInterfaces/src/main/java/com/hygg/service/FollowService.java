package com.hygg.service;

import java.util.Map;

public interface FollowService {
    public Map<String,Object> follow(int followerId,int followedUserId);
    public Map<String,Object> unFollow(int followerId,int followedUserId);
    public Map<String,Object> getFollowerVOs(int pageNum,int pageSize,int userId);
    public Map<String,Object> getFollowingVOs(int pageNum,int pageSize,int userId);
    public Map<String,Object> getPeopleYouMayKnowVOs(int userId);
    public boolean followFlag(int followerId,int followedUserId);
}

