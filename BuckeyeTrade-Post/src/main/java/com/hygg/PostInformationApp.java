package com.hygg;

import com.hygg.entity.PostInformation;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = {"com.hygg.dao"}, annotationClass = Repository.class)
@EnableDubbo
public class PostInformationApp {
    public static void main(String[] args){
        SpringApplication.run(PostInformationApp.class,args);
    }
}
