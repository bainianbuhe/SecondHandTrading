package com.hygg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hygg.dao.PostInformationMapper;
import com.hygg.dto.NewPostDTO;
import com.hygg.entity.PostInformation;
import com.hygg.entity.PostInformationExample;
import com.hygg.entity.User;
import com.hygg.service.PostInformationService;
import com.hygg.service.UserService;
import com.hygg.vo.ItemCardVO;
import com.hygg.vo.ItemDetailVO;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostInformationServiceImpl implements PostInformationService {
    @Reference(version="0.1")
    UserService userService;
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
        String[] imgNames=new String[suffixes.length];
        for(int i=0;i<files.length;i++){
            readBin2Image(files[i],folderDir+"/"+i+suffixes[i]);
            imgNames[i]=i+1+ suffixes[i];
        }
        String imgNamesString = StringUtils.join(imgNames, ",");
        return new HashMap<String,Object>(){
            {
                put("result","success");
                put("folderUrl",currentServerAddress+"/static/post-photos/"+timeStamp+"-"+id);
                put("imgNames", imgNamesString);
            }
        };

    }

    @Override
    public Map<String, Object> uploadInformation( NewPostDTO newPostDTO) {
        PostInformation postInformation=new PostInformation(){
            {
                setImgNames(newPostDTO.getImgNames());
                setTag(newPostDTO.getTag());
                setAuthorId(newPostDTO.getAuthorId());
                setContact(newPostDTO.getContact());
                setDescription(newPostDTO.getDescription());
                setFolderUrl(newPostDTO.getFolderUrl());
                setItemName(newPostDTO.getItemName());
                setPrice(newPostDTO.getPrice());
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

    @Override
    public Map<String, Object> uploadInformation(PostInformation postInformation) {
        postInformationMapper.insert(postInformation);
        return null;
    }
    @Override
    public Map<String,Object> getItemCardVOs(String tag, int pageNum,int pageSize){
        PostInformationExample postInformationExample=new PostInformationExample();
        PostInformationExample.Criteria criteria=postInformationExample.createCriteria();
        if(!(tag.equals("Recent posts"))) {
            criteria.andTagEqualTo(tag);
        }
        postInformationExample.setOrderByClause("post_time desc , price  desc");
        PageHelper.startPage(pageNum,pageSize);
        List<PostInformation> postInformationList= postInformationMapper.selectByExample(postInformationExample);
        List<ItemCardVO> itemCardVOList=new ArrayList<ItemCardVO>();
        for(int i=0;i<postInformationList.size();i++){
            ItemCardVO itemCardVO=new ItemCardVO();
            PostInformation postInformation=postInformationList.get(i);
            int authorId=postInformation.getAuthorId();
            User author=userService.queryUserById(authorId);
            itemCardVO.setAuthorAvatarUrl(author.getAvatarUrl());
            itemCardVO.setAuthorName(author.getUserName());
            Date date=postInformation.getPostTime();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            String dateTime=simpleDateFormat.format(date);
            itemCardVO.setDate(dateTime);
            String imgNames=postInformation.getImgNames();
            String[] imgNameArray=imgNames.split(",");
            itemCardVO.setImgUrl(postInformation.getFolderUrl()+imgNameArray[0]);
            itemCardVO.setItemName(postInformation.getItemName());
            itemCardVO.setPrice("$"+postInformation.getPrice());
            itemCardVO.setLabel(postInformation.getTag());
            itemCardVO.setPostId(postInformation.getPostId());
            itemCardVOList.add(itemCardVO);
        }
        PageInfo pageInfo=new PageInfo(postInformationList);
        long pageCount=pageInfo.getTotal();
        return new HashMap<String,Object>(){
            {
                put("pageCount",pageCount);
                put("itemCardVOs",itemCardVOList);
            }
        };
    }

    @Override
    public Map<String, Object> getItemDetailVO(int id) {
        PostInformation postInformation=postInformationMapper.selectByPrimaryKey(id);
        ItemDetailVO itemDetailVO=new ItemDetailVO();
        int authorId=postInformation.getAuthorId();
        User author=userService.queryUserById(authorId);
        itemDetailVO.setAuthorAvatarUrl(author.getAvatarUrl());
        itemDetailVO.setAuthorName(author.getUserName());
        itemDetailVO.setContact(postInformation.getContact());
        Date date=postInformation.getPostTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
        String dateTime=simpleDateFormat.format(date);
        itemDetailVO.setPostTime(dateTime);
        itemDetailVO.setDescription(postInformation.getDescription());
        String imgNames=postInformation.getImgNames();
        String[] imgNameArray=imgNames.split(",");
        String[] imgUrls=new String[imgNameArray.length];
        for(int i=0;i< imgNameArray.length;i++){
            imgUrls[i]=postInformation.getFolderUrl()+imgNameArray[i];
        }
        itemDetailVO.setImgUrls(imgUrls);
        itemDetailVO.setItemName(postInformation.getItemName());
        itemDetailVO.setPrice("$"+postInformation.getPrice());
        itemDetailVO.setTag(postInformation.getTag());
        return new HashMap<String,Object>(){
            {
                put("message","success");
                put("itemDetailVO",itemDetailVO);
            }
        };
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
