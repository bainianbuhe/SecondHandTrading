package com.hygg.service;

import java.util.Map;

public interface AvatarUploadService {
    public Map<String,Object> upload(byte[] fileByteArray,String fileType);

}
