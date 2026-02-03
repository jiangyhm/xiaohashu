package com.jiangy.xiaohashu.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.jiangy.framework.common.enums.DeletedEnum;
import com.jiangy.framework.common.enums.StatusEnum;
import com.jiangy.framework.common.exception.BizException;
import com.jiangy.framework.common.response.Response;
import com.jiangy.framework.common.util.JsonUtils;
import com.jiangy.xiaohashu.auth.constant.RedisKeyConstants;
import com.jiangy.xiaohashu.auth.constant.RoleConstants;
import com.jiangy.xiaohashu.auth.enums.LoginTypeEnum;
import com.jiangy.xiaohashu.auth.enums.ResponseCodeEnum;
import com.jiangy.xiaohashu.auth.model.vo.user.UpdatePasswordReqVO;
import com.jiangy.xiaohashu.auth.model.vo.user.UserLoginReqVO;
import com.jiangy.xiaohashu.auth.rpc.UserRpcService;
import com.jiangy.xiaohashu.auth.service.AuthService;
import com.jiangy.xiaohashu.framework.biz.context.holder.LoginUserContextHolder;
import com.jiangy.xiaohashu.user.dto.resp.FindUserByPhoneRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {



    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRpcService userRpcService;

    /**
     * 登录与注册
     *
     * @param userLoginReqVO
     * @return
     */
    @Override
    public Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO) {
        String phone = userLoginReqVO.getPhone();
        Integer type = userLoginReqVO.getType();

        // 1. 契约校验
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);

        if (Objects.isNull(loginTypeEnum)) {
            throw new BizException(ResponseCodeEnum.LOGIN_TYPE_ERROR);
        }

        Long userId = null;

        switch (loginTypeEnum) {

            case VERIFICATION_CODE:

                String verificationCode = userLoginReqVO.getCode();

                // 2. 用户输入校验
                if (StringUtils.isBlank(verificationCode)) {
                    return Response.fail("验证码不能为空");
                }

                String key = RedisKeyConstants.buildVerificationCodeKey(phone);
                String sendCode = (String) redisTemplate.opsForValue().get(key);

                // 3. 业务可预期失败
                if (!StringUtils.equals(sendCode, verificationCode)) {
                    return Response.fail("验证码错误");
                }

                Long userIdTmp = userRpcService.registerUser(phone);

                if(Objects.isNull(userIdTmp)) {
                    throw new BizException(ResponseCodeEnum.LOGIN_FAIL);
                }

                userId = userIdTmp;
                break;

            case PASSWORD:

                String password = userLoginReqVO.getPassword();

                if (StringUtils.isBlank(password)) {
                    return Response.fail("密码不能为空");
                }

                FindUserByPhoneRspDTO findUserByPhoneRspDTO = userRpcService.findUserByPhone(phone);


                if (Objects.isNull(findUserByPhoneRspDTO)) {
                    throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
                }

                String encodePassword = findUserByPhoneRspDTO.getPassword();

                // 未设置密码 - 防守性编程
                if (StringUtils.isBlank(encodePassword)) {
                    throw new BizException(ResponseCodeEnum.PHONE_OR_PASSWORD_ERROR);
                }

                boolean isPasswordCorrect = passwordEncoder.matches(password, encodePassword);

                if (!isPasswordCorrect) {
                    throw new BizException(ResponseCodeEnum.PHONE_OR_PASSWORD_ERROR);
                }

                userId = findUserByPhoneRspDTO.getId();
                break;

            default:
                break;
        }

        // 5. 登录属于核心流程异常
        StpUtil.login(userId);

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return Response.success(tokenInfo.tokenValue);

    }

    @Override
    public Response<?> logout() {
        Long userId = LoginUserContextHolder.getUserId();
        log.info("==> 用户退出登录, userId: {}", userId);
        // 退出登录 (指定用户 ID)
        StpUtil.logout(userId);
        threadPoolTaskExecutor.submit(() -> {
            Long userId1 = LoginUserContextHolder.getUserId();
            log.info("异步线程池中获取 userId：{}", userId1);
        });
        return Response.success();
    }

    @Override
    public Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO) {
        String newPassword = updatePasswordReqVO.getNewPassword();
        String encodePassword = passwordEncoder.encode(newPassword);

        userRpcService.updatePassword(encodePassword);

        return Response.success();
    }

}

