package com.hygg.service;

import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;

import java.util.Map;

public interface PostInformationService {
    public Map<String,Object> uploadPhotos(byte[][] files, int id,String[] suffixes);
    public Map<String,Object>  uploadInformation(NewPostDTO newPostDTO);
    public PostInformation getPostInformation(Integer id);
    public Map<String,Object> updatePostInformation(PostInformation postInformation);
    public Map<String,Object> refreshPost(Integer id);
    public Map<String,Object> uploadInformation(PostInformation postInformation);
    public Map<String,Object> getItemCardVOs(String tag,int pageNum,int pageSize);
    public Map<String,Object> getItemDetailVO(int id);
    public Map<String,Object> searchByKeyWord(String tag,int pageNum,int pageSize,String keyWord);
    public Map<String,Object> soldPostsById(int pageNum, int pageSize,int userId);
    public Map<String,Object> unsoldPostsById(int pageNum, int pageSize,int userId);
    public Map<String, Object>  markAsSold(int postId);

}
