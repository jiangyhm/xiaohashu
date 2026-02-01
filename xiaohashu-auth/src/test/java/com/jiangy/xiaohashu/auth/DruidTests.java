package com.jiangy.xiaohashu.auth;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @Description TODO
 * @Author jiangy
 * @Date 2026/2/1 18:35
 **/
@SpringBootTest
@Slf4j
public class DruidTests {
    /**
     * 数据库密码加密保证安全
     */
    @Test
    @SneakyThrows
    void testEncodePassword() {
        String password = "123456";
        String[] arr = ConfigTools.genKeyPair(512);

        // 私钥
        log.info("privateKey: {}", arr[0]);
        // 公钥
        log.info("publicKey: {}", arr[1]);

        // 通过私钥加密密码
        String encodePassword = ConfigTools.encrypt(arr[0], password);
        log.info("password: {}", encodePassword);
    }
}
