package com.jiangy.xiaohashu.oss.biz.enums;

import com.jiangy.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangy
 */

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    /**
     *
     */
    SYSTEM_ERROR("OSS-10000", "出错啦，后台小哥正在努力修复中..."),
    PARAM_NOT_VALID("OSS-10001", "参数出错"),
    ;
    private final String errorCode;
    private final String errorMessage;
}
