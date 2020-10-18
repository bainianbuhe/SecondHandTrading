package com.hygg.service;

import com.hygg.dto.NotificationDTO;
import com.hygg.entity.Notification;

import java.util.Map;

public interface NotificationService {
    public Map<String,Object> insertNotification(NotificationDTO notificationDTO);
    public Map<String,Object> listReadNotificationVOs(int pageNum,int pageSize,int recipientId);
    public Map<String,Object> listUnreadNotificationVOs(int pageNum,int pageSize,int recipientId);
    public Map<String, Object> markAsRead(int notificationId);

}
