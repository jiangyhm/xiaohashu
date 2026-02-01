package com.jiangy.xiaohashu.auth.service;

import com.jiangy.framework.common.response.Response;
import com.jiangy.xiaohashu.auth.model.vo.verificationcode.SendVerificationCodeReqVO;

public interface VerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
