package com.rednote.leaf.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Jason Feng
 * @since 2026-03-01 20:10
 */
@Slf4j
@SpringBootApplication
public class RedNoteLeafBizApplication {

    public static void main( String[] args ) {
        log.info("RedNote-Leaf-Biz 开始启动");
        SpringApplication.run(RedNoteLeafBizApplication.class, args);
        log.info("RedNote-Leaf-Biz 启动完成");
    }
}
