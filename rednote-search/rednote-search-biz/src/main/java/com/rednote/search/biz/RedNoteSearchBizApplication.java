package com.rednote.search.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 搜索服务启动类
 *
 * @since 2026-03-22 14:38
 * @author Jason Feng
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class RedNoteSearchBizApplication {

    public static void main(String[] args) {
        log.info("RedNote-Search-Biz开始启动！");
        SpringApplication.run(RedNoteSearchBizApplication.class, args);
        log.info("RedNote-Search-Biz启动完成！");
    }
}
