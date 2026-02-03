package com.jiangy.xiaohashu.oss.biz.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jiangy.framework.common.response.Response;
import com.jiangy.xiaohashu.framework.biz.context.holder.LoginUserContextHolder;
import com.jiangy.xiaohashu.oss.biz.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Description 文件上传控制器
 * @Author jiangy
 * @Date 2026/2/3 12:51
 **/
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Resource
    private FileService fileService;

    private static final String BUCKET_NAME = "xiaohashu";

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<?> uploadFile(@RequestPart(value = "file")MultipartFile file) {
        log.info("当前用户ID：{}", LoginUserContextHolder.getUserId());
        return fileService.uploadFile(file);
    }
}
