package com.jiangy.xiaohashu.oss.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @Description TODO
 * @Author jiangy
 * @Date 2026/2/3 16:44
 **/
@ConfigurationProperties(prefix = "storage.aliyun-oss")
@Component
@Data
public class AliyunOssProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}
