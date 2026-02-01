package com.jiangy.framework.common.exception;

/**
 *
 * @Description 通用异常接口
 * @Author jiangy
 * @Date 2026/1/31 14:04
 **/
public interface BaseExceptionInterface {
    /**
     * 获取异常码
     * @return 返回异常码
     */
    String getErrorCode();

    /**
     * 获取异常信息
     * @return 返回异常信息
     */
    String getErrorMessage();
}
