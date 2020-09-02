package com.hygg.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.hygg.utils.StreamOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.hygg.service.AvatarUploadService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
@Service(protocol = "hessian")
public class AvatarUploadServiceImpl implements AvatarUploadService {
    @Value("${fileUpload.avatar.resourceLocation}")
    String resourceLocation;
    @Value("${myServer.address}")
    String currentServerAddress;
    @Override
    public Map<String, Object> upload(InputStream inputStream) {
        Logger logger = LoggerFactory.getLogger(AvatarUploadServiceImpl.class);
        logger.info("AvatarUploadService");
        try{
            /*
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            file.transferTo(new File(resourceLocation+hashString));
             */
            StreamOperation.writeToLocal(resourceLocation+"asdasd",inputStream);


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

