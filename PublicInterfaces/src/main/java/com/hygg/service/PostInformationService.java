package com.hygg.service;

import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;

import java.util.Map;

public interface PostInformationService {
    public Map<String,Object> uploadPhotos(byte[][] files, int id,String[] suffixes);
    public Map<String,Object>  uploadInformation(NewPostDTO newPostDTO);
    public Map<String,Object> getPostInformation(Integer id);
    public Map<String,Object> updatePostInformation(PostInformation postInformation);
    public Map<String,Object> refreshPost(Integer id);
}
