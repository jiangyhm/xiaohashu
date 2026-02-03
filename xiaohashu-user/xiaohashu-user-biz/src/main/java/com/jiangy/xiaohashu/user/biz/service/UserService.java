package com.jiangy.xiaohashu.user.biz.service;

import com.jiangy.framework.common.response.Response;
import com.jiangy.xiaohashu.user.biz.model.vo.UpdateUserInfoReqVO;
import com.jiangy.xiaohashu.user.dto.req.FindUserByPhoneReqDTO;
import com.jiangy.xiaohashu.user.dto.req.RegisterUserReqDTO;
import com.jiangy.xiaohashu.user.dto.req.UpdateUserPasswordReqDTO;
import com.jiangy.xiaohashu.user.dto.resp.FindUserByPhoneRspDTO;

/**
 *
 * @Description 用户业务
 * @Author jiangy
 * @Date 2026/2/3 17:59
 **/
public interface UserService {
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    Response<Long> register(RegisterUserReqDTO registerUserReqDTO);

    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    Response<FindUserByPhoneRspDTO> findByPhone(FindUserByPhoneReqDTO findUserByPhoneReqDTO);

    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    Response<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
}
