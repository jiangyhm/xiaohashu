package com.jiangy.xiaohashu.oss.biz.strategy;

import com.jiangy.framework.common.response.Response;
import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Description 文件上传策略接口
 * @Author jiangy
 * @Date 2026/2/3 12:42
 **/
public interface FileStrategy {
    /**
     * 文件上传
     * @param file
     * @param bucketName
     * @return
     */
    String uploadFile(MultipartFile file, String bucketName);
}
