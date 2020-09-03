package com.hygg;

import com.hygg.utils.YamlPropertySourceFactory;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDubbo
@PropertySource(value={"classpath:application2.yml"},factory= YamlPropertySourceFactory.class)
public class AvatarUploadApp {
    public static void main(String[] args){
        SpringApplication.run(AvatarUploadApp.class,args);
    }
}
