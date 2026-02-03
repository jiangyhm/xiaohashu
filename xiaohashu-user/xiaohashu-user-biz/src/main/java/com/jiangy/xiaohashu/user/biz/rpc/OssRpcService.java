package com.jiangy.xiaohashu.user.biz.rpc;

import com.jiangy.framework.common.response.Response;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import com.jiangy.xiaohashu.oss.api.FileFeignApi;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @Description 封装文件上传客户端
 * @Author jiangy
 * @Date 2026/2/3 18:55
 **/
@Component
public class OssRpcService {

    @Resource
    private FileFeignApi fileFeignApi;

    public String uploadFile(MultipartFile file) {
        // 调用对象存储服务上传文件
        Response<?> response = fileFeignApi.uploadFile(file);

        if (!response.isSuccess()) {
            return null;
        }

        // 返回图片访问链接
        return (String) response.getData();
    }
}
