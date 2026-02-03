package com.jiangy.xiaohashu.oss.biz.service.impl;

import com.jiangy.framework.common.response.Response;
import com.jiangy.xiaohashu.oss.biz.service.FileService;
import com.jiangy.xiaohashu.oss.biz.strategy.FileStrategy;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Description 文件服务实现类
 * @Author jiangy
 * @Date 2026/2/3 12:49
 **/
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileStrategy fileStrategy;

    @Override
    public Response<?> uploadFile(MultipartFile file) {
        String url = fileStrategy.uploadFile(file, "xiaohashujj");

        return Response.success(url);
    }
}
