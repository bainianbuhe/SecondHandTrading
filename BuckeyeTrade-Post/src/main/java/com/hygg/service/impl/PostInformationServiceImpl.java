package com.hygg.service.impl;

import com.hygg.dao.PostInformationMapper;
import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;
import com.hygg.service.PostInformationService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class PostInformationServiceImpl implements PostInformationService {
    @Autowired
    private PostInformationMapper postInformationMapper;
    Logger logger= LoggerFactory.getLogger(PostInformationServiceImpl.class);
    @Value("${fileUpload.post.resourceLocation}")
    private String postResourceLocation;
    @Value("${myServer.address}")
    String currentServerAddress;
    @Override
    public Map<String, Object> uploadPhotos(byte[][] files,int id,String[] suffixes) {
        Long timeStamp=System.currentTimeMillis();
        String folderDir=postResourceLocation+timeStamp+"-"+id;
        File file=new File(folderDir);
        try{
            file.mkdirs();
            }
        catch(Exception e){
            return new HashMap<String,Object>(){
                {
                    put("result","failure");
                    put("message",e.toString());
                }
            };
            }
        logger.info("picnum:"+files.length);
        for(int i=0;i<files.length;i++){
            readBin2Image(files[i],folderDir+"/"+i+suffixes[i]);
        }
        return new HashMap<String,Object>(){
            {
                put("result","success");
                put("folderUrl",currentServerAddress+"/static/post-photos/"+timeStamp+"-"+id);
            }
        };

    }

    @Override
    public Map<String, Object> uploadInformation( NewPostDTO newPostDTO) {
        PostInformation postInformation=new PostInformation(){
            {
                setContact(newPostDTO.getContact());
                setDescription(newPostDTO.getDescription());
                setFolderUrl(newPostDTO.getFolderUrl());
                setImgNum(newPostDTO.getImgNum());
                setItemName(newPostDTO.getItemName());
                setPostTime(new Date());
                setStatus(0);
            }
        };
        postInformationMapper.insert(postInformation);
        return new HashMap<String,Object>(){
            {
                put("message","success");
            }
        };
    }

    @Override
    public Map<String, Object> getPostInformation(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> updatePostInformation(PostInformation postInformation) {
        return null;
    }

    @Override
    public Map<String, Object> refreshPost(Integer id) {
        return null;
    }

    private static void readBin2Image(byte[] byteArray, String targetPath) {
        Logger logger= LoggerFactory.getLogger(PostInformationServiceImpl.class);
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
            logger.error(e.toString());
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
