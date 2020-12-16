package com.hygg.controller;

import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;
import com.hygg.entity.User;
import com.hygg.service.PostInformationService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.NewThreadAction;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PostInformationController {
    Logger logger= LoggerFactory.getLogger(PostInformationController.class);
    @Reference(version="0.1")
    private PostInformationService postInformationService;
    @PostMapping("/upload/post-photos")
    public Map<String,Object> uploadPhotos(@RequestParam(value = "files") MultipartFile[] files,
                                           @RequestParam(value="id") int id){
        int fileNum=files.length;
        logger.info("filesnum:"+fileNum);
        byte[][] fileBytesArrays=new byte[fileNum][];
        String[] suffixes=new String[fileNum];
        for(int i=0;i<fileNum;i++){
            String fileName=files[i].getOriginalFilename();
            suffixes[i]=fileName.substring(fileName.lastIndexOf('.'));
        }
        try{
            for(int i=0;i<files.length;i++){
                fileBytesArrays[i]=files[i].getBytes();
            }
        }
        catch (Exception e){
            HashMap<String,Object> result=new HashMap<>();
            result.put("result","failure");
            result.put("message",e.toString());
            return result;
        }
        return postInformationService.uploadPhotos(fileBytesArrays,id,suffixes);

    }
    @PostMapping("/new-post")
    public Map<String,Object> uploadPost(@RequestBody NewPostDTO newPostDTO){
        return postInformationService.uploadInformation(newPostDTO);
    }
    @GetMapping("/get-item-vos")
    public Map<String,Object> getItemVOs(@RequestParam(value="tag") String tag,
                                         @RequestParam(value="pageNum") int pageNum,
                                         @RequestParam(value="pageSize")int pageSize,
                                         @RequestParam(value="userId") int userId){
        logger.info("received params"+tag+"and"+pageNum+"and"+pageSize);
        return postInformationService.getItemCardVOs(tag,pageNum,pageSize,userId);
    }
    @GetMapping("/get-item-detail-VO")
    public Map<String,Object> getItemDetailVO(@RequestParam(value = "postId") int postId){
        return postInformationService.getItemDetailVO(postId);
    }
    @GetMapping("/search-by-keyword")
    public Map<String,Object> searchByKeyWord(@RequestParam(value="tag") String tag,
                                              @RequestParam(value="pageNum") int pageNum,
                                              @RequestParam(value="pageSize")int pageSize,
                                              @RequestParam(value="keyword") String keyWord,
                                              @RequestParam(value="userId") int userId){
        return postInformationService.searchByKeyWord(tag,pageNum,pageSize,keyWord,userId);
    }
    @GetMapping("/sold-posts-by-id")
    public Map<String,Object> soldPostsById(@RequestParam(value="userId") int userId,
                                            @RequestParam(value="pageNum") int pageNum,
                                            @RequestParam(value="pageSize") int pageSize){
        return postInformationService.soldPostsById(pageNum, pageSize,userId);
    }
    @GetMapping("/unsold-posts-by-id")
    public Map<String,Object> unsoldPostsById(@RequestParam(value="userId") int userId,
                                              @RequestParam(value="pageNum") int pageNum,
                                              @RequestParam(value="pageSize") int pageSize){
        return postInformationService.unsoldPostsById(pageNum,  pageSize,userId);
    }
    @GetMapping("/mark-as-sold")
    public Map<String,Object> markAsSold(@RequestParam(value="postId") int postId){
        return postInformationService.markAsSold(postId);
    }
 }
