package service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AvatarUploadService {
    public Map<String,Object> upload(MultipartFile file);

}
