package com.jiangy.xiaohashu.framework.biz.context.config;

import com.jiangy.xiaohashu.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author: jiangy
 * @description: Feign 请求拦截器自动配置
 **/
@AutoConfiguration
public class FeignContextAutoConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
