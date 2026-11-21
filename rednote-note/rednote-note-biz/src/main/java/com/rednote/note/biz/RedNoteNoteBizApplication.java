package com.rednote.note.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 笔记服务启动类
 *
 * @since 2026-01-31 16:14
 * @author Jason Feng
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rednote")
public class RedNoteNoteBizApplication {

    public static void main(String[] args) {
        log.info("RedNote-Note-Biz开始启动！");
        SpringApplication.run(RedNoteNoteBizApplication.class, args);
        log.info("RedNote-Note-Biz启动完成！");
    }
}
