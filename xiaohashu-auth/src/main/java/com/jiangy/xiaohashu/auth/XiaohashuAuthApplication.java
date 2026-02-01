package com.jiangy.xiaohashu.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiangy.xiaohashu.auth.domain.mapper")
public class XiaohashuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohashuAuthApplication.class, args);
    }

}
