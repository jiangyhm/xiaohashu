package com.jiangy.xiaohashu.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @Description 用户服务主启动类
 * @Author jiangy
 * @Date 2026/2/3 17:18
 **/
@SpringBootApplication
@MapperScan("com.jiangy.xiaohashu.user.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.jiangy.xiaohashu")
public class XiaohashuUserBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaohashuUserBizApplication.class, args);
    }
}
