package com.hygg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.hygg.service.AvatarUploadService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class AvatarUploadController {
    @Reference(timeout = 10000,version = "1.0.0",protocol = "hessian")
    private AvatarUploadService avatarUploadService;
    @PostMapping("/avatar")
    @CrossOrigin("http://localhost:8080")
    public Map<String,Object> uploadAvatar(
            @RequestParam(value = "file",required = false)
                    MultipartFile file){
        Logger logger = LoggerFactory.getLogger(AvatarUploadController.class);
        logger.info("AvatarUploadService");
        logger.info(avatarUploadService.toString());
        InputStream inputStream=null;
        try{
            inputStream=file.getInputStream();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                inputStream.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return avatarUploadService.upload(inputStream);
    }
}
