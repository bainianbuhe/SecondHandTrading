package com.hygg.controller;

import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;
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
            return new HashMap<String,Object>(){
                {
                    put("result","failure");
                    put("message",e.toString());
                }
            };
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
                                         @RequestParam(value="pageSize")int pageSize){
        logger.info("received params"+tag+"and"+pageNum+"and"+pageSize);
        return postInformationService.getItemCardVOs(tag,pageNum,pageSize);
    }
    @GetMapping("/get-item-detail-VO")
    public Map<String,Object> getItemDetailVO(@RequestParam(value = "postId") int postId){
        return postInformationService.getItemDetailVO(postId);
    }
}
