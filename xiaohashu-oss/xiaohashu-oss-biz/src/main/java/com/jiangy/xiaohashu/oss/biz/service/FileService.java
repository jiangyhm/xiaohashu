package com.jiangy.xiaohashu.oss.biz.service;

import com.jiangy.framework.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Description 文件服务
 * @Author jiangy
 * @Date 2026/2/3 12:48
 **/
public interface FileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Response<?> uploadFile(MultipartFile file);
}
