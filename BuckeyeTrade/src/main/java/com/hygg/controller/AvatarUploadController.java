package com.hygg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.AvatarUploadService;

import java.util.Map;

@RestController
@RequestMapping("/upload")
public class AvatarUploadController {
    @Reference
    private AvatarUploadService avatarUploadService;
    @PostMapping("/avatar")
    @CrossOrigin("http://localhost:8080")
    public Map<String,Object> uploadAvatar(
            @RequestParam(value = "file",required = false)
                    MultipartFile file){
        return avatarUploadService.upload(file);
    }
}
