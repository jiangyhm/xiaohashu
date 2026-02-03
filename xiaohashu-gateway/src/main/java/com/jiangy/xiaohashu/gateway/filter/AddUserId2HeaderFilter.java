package com.jiangy.xiaohashu.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.jiangy.framework.common.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: 犬小哈
 * @date: 2024/4/9 15:52
 * @version: v1.0.0
 * @description: 转发请求时，将用户 ID 添加到 Header 请求头中，透传给下游服务
 **/
@Component
@Slf4j
public class AddUserId2HeaderFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 优先级设置为 -99，确保在 Sa-Token 过滤器（-100）之后执行
        return -99;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==================> AddUserId2HeaderFilter");

        // 用户 ID
        Long userId = null;
        try {
            // 获取当前登录用户的 ID
            userId = StpUtil.getLoginIdAsLong();
            log.info("## 当前登录的用户 ID: {}", userId);
        } catch (Exception e) {
            // 若没有登录，则直接放行
            log.warn("## 获取用户ID失败，请求将不携带userId，异常信息: {}", e.getMessage());
            return chain.filter(exchange);
        }
        Long finalUserId = userId;
        ServerWebExchange newExchange = exchange.mutate()
                .request(builder -> builder.header(GlobalConstants.USER_ID, String.valueOf(finalUserId))) // 将用户 ID 设置到请求头中
                .build();
        return chain.filter(newExchange);
    }
}
