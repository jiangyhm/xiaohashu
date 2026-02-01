package com.jiangy.xiaohashu.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.jiangy.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiangy.framework.common.response.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 *
 * @Description TODO
 * @Author jiangy
 * @Date 2026/1/31 14:12
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response<String> test() {
        return Response.success("Hello World");
    }

    @PostMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test2(@RequestBody @Validated User user) {

        int i = 1/0;
        return Response.success(user);
    }
    // 测试登录，浏览器访问： http://localhost:8080/user/doLogin?username=zhang&password=123456
    @RequestMapping("/user/doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("/user/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
