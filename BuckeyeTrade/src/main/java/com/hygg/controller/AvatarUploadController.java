package com.hygg.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.hygg.service.AvatarUploadService;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class AvatarUploadController {
    @Reference
    private AvatarUploadService avatarUploadService;
    @PostMapping("/avatar")
    public Map<String,Object> uploadAvatar(
            @RequestParam(value = "file",required = false)
                    MultipartFile file){
        Logger logger = LoggerFactory.getLogger(AvatarUploadController.class);
        logger.info("begin getting bytes");
        try{
            String fileName=file.getOriginalFilename();
            return avatarUploadService.upload(file.getBytes(),fileName.substring(fileName.lastIndexOf(".")+1));
        }
        catch (IOException e){
            logger.info("IOexeption");
            e.printStackTrace();
        }
        return new HashMap<String,Object>(){{
            put("message","failed to get bytes");
        }};

    }
}
