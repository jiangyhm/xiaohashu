package com.jiangy.xiaohashu.framework.biz.context.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.jiangy.framework.common.constant.GlobalConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @Description 登录用户上下文
 * @Author jiangy
 * @Date 2026/2/3 11:35
 **/
public class LoginUserContextHolder {
    private static final ThreadLocal<Map<String, Object>> LOGIN_USER_CONTEXT_THREAD_LOCAL
            = TransmittableThreadLocal.withInitial(HashMap::new);
    // 设置用户ID
    public static void setUserId(Object value) {
        LOGIN_USER_CONTEXT_THREAD_LOCAL.get().put(GlobalConstants.USER_ID, value);
    }
    // 获取用户ID
    public static Long getUserId() {
        Object value = LOGIN_USER_CONTEXT_THREAD_LOCAL.get().get(GlobalConstants.USER_ID);
        if(Objects.isNull(value)) {
            return null;
        }
        return Long.valueOf(value.toString());
    }

    // 删除用户ID
    public static void remove() {
        LOGIN_USER_CONTEXT_THREAD_LOCAL.remove();
    }
}
