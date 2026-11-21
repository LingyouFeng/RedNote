package com.rednote.comment.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 评论服务启动类
 *
 * @since 2026-03-23 17:35
 * @author Jason Feng
 */
@Slf4j
@EnableRetry
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rednote")
public class RedNoteCommentBizApplication {

    public static void main( String[] args ) {
        log.info("RedNote-Comment-Biz开始启动！");
        SpringApplication.run(RedNoteCommentBizApplication.class, args);
        log.info("RedNote-Comment-Biz启动完成！");
    }
}
