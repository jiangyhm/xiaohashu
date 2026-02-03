package com.jiangy.xiaohashu.auth.service;

import com.jiangy.framework.common.response.Response;
import com.jiangy.xiaohashu.auth.model.vo.user.UpdatePasswordReqVO;
import com.jiangy.xiaohashu.auth.model.vo.user.UserLoginReqVO;

/**
 * @author jiangy
 */
public interface AuthService {

    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO);

    Response<?> logout();

    Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO);
}

