package com.rednote.kv.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Jason Feng
 * @since 2026-03-01 11:04
 */
@Slf4j
@SpringBootApplication
public class RedNoteKVBizApplication {

    public static void main(String[] args) {
        log.info("RedNote-KV-Biz 开始启动");
        SpringApplication.run(RedNoteKVBizApplication.class, args);
        log.info("RedNote-KV-Biz 启动完成");
    }

}
