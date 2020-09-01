package com.hygg.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import service.AvatarUploadService;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@Service(protocol = "hessian")
public class AvatarUploadServiceImpl implements AvatarUploadService {
    @Value("${fileUpload.avatar.resourceLocation}")
    String resourceLocation;
    @Value("${myServer.address}")
    String currentServerAddress;
    @Override
    public Map<String, Object> upload(MultipartFile file) {
        Logger logger = LoggerFactory.getLogger(AvatarUploadServiceImpl.class);
        logger.info("AvatarUploadService");
        if (file.isEmpty()) return new HashMap<String,Object>() {
            {
                put("success",false);
                put("message","empty file");
            }
        };
        logger.info("begin serializing!");
        try{
            /*
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            file.transferTo(new File(resourceLocation+hashString));
             */
            file.transferTo(new File(resourceLocation+"hashString"));
        }
        catch(Exception e){
            logger.error("error!");
            e.printStackTrace();
            return new HashMap<String,Object>() {
                {
                    put("success",false);
                    put("message","failure");
                }
            };
        }
        return new HashMap<String,Object>() {
            {
                put("success",true);
                put("message","success");
                put("serverAddress",currentServerAddress);
            }
        };
    }
}

