package com.hygg.service.impl;
import com.hygg.service.AvatarUploadService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Service
//@PropertySource(value = {"classpath:application2.yml"})
public class AvatarUploadServiceImpl implements AvatarUploadService {
    @Value("${fileUpload.avatar.resourceLocation}")
    String resourceLocation;
    @Value("${myServer.address}")
    String currentServerAddress;
    @Override
    public Map<String, Object> upload(byte[] fileByteArray,String fileType) {
        String filePath="";
        String fileName="";
        if (fileByteArray.length==0) return new HashMap<String,Object>() {
            {
                put("success",false);
                put("message","empty file");
            }
        };
        try{

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(fileByteArray);
            String hashString = new BigInteger(1, digest).toString(16);
            fileName=hashString+"."+fileType;
            filePath=resourceLocation+fileName;
            readBin2Image(fileByteArray,filePath);

        }
        catch(Exception e){
            e.printStackTrace();
            return new HashMap<String,Object>() {
                {
                    put("success",false);
                    put("message",e.toString());
                }
            };
        }
        String finalFileName = fileName;
        return new HashMap<String,Object>() {
            {
                put("success",true);
                put("imgUrl",currentServerAddress+"/static/avatars/"+finalFileName);
            }
        };
    }
    private static void readBin2Image(byte[] byteArray, String targetPath) {
        InputStream in = new ByteArrayInputStream(byteArray);
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

