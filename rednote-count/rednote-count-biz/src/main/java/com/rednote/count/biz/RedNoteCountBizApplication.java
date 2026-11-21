package com.rednote.count.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 计数服务启动类
 *
 * @author Jason Feng
 * @since 2026-03-13 16:33
 */
@Slf4j
@SpringBootApplication
// @EnableFeignClients(basePackages = "com.rednote")
public class RedNoteCountBizApplication {

    public static void main( String[] args ) {
        log.info("RedNote-Count-Biz开始启动！");
        SpringApplication.run(RedNoteCountBizApplication.class, args);
        log.info("RedNote-Count-Biz启动完成！");
    }
}
