package com.rednote.user.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户登录、认证模块启动类
 *
 * @since 2026-01-31 16:14
 * @author Jason Feng
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rednote")
public class RedNoteUserBizApplication {

    public static void main(String[] args) {
        log.info("RedNote-User-Biz开始启动！");
        SpringApplication.run(RedNoteUserBizApplication.class, args);
        log.info("RedNote-User-Biz启动完成！");
    }

}
