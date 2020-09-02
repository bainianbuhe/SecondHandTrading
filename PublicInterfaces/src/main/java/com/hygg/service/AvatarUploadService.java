package com.hygg.service;

import java.io.InputStream;
import java.util.Map;

public interface AvatarUploadService {
    public Map<String,Object> upload(InputStream inputStream);

}
