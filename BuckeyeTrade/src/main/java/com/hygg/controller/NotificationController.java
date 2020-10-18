package com.hygg.controller;

import com.hygg.service.NotificationService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotificationController {
    @Reference(version = "0.1")
    NotificationService notificationService;
    @GetMapping("/read-notifications")
    public Map<String, Object> readNotifications(@RequestParam(value="pageNum") int pageNum,
                                                 @RequestParam(value="pageSize") int pageSize,
                                                 @RequestParam(value="userId") int userId){
        return notificationService.listReadNotificationVOs(pageNum,pageSize,userId);
    }
    @GetMapping("/unread-notifications")
    public Map<String, Object> unreadNotifications(@RequestParam(value="pageNum") int pageNum,
                                                 @RequestParam(value="pageSize") int pageSize,
                                                 @RequestParam(value="userId") int userId){
        return notificationService.listUnreadNotificationVOs(pageNum,pageSize,userId);
    }
    @GetMapping("/mark-as-read")
    public Map<String, Object> markAsRead(@RequestParam(value="notificationId") int notificationId){
        return notificationService.markAsRead(notificationId);
    }
}
