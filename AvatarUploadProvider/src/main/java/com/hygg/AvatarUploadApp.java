package com.hygg;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDubbo
public class AvatarUploadApp {
    public static void main(String[] args){
        SpringApplication.run(AvatarUploadApp.class,args);
    }
}
