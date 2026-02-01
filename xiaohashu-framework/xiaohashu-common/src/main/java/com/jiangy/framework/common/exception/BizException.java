package com.jiangy.framework.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @Description 业务异常
 * @Author jiangy
 * @Date 2026/1/31 14:05
 **/
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
