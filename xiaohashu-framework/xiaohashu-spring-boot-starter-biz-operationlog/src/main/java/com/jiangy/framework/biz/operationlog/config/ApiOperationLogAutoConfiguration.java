package com.jiangy.framework.biz.operationlog.config;

import com.jiangy.framework.biz.operationlog.aspect.ApiOperationLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 *
 * @Description 向容器中注入切面
 * @Author jiangy
 * @Date 2026/1/31 14:43
 **/
@AutoConfiguration
public class ApiOperationLogAutoConfiguration {
    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }
}
