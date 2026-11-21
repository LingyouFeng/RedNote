package com.rednote.data.align;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 数据对齐启动类
 *
 * @author Jason Feng
 * @since 2026-03-16 19:42
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rednote")
public class RedNoteDataAlignApplication {

    public static void main(String[] args) {
        log.info("RedNote-DataAlign开始启动！");
        SpringApplication.run(RedNoteDataAlignApplication.class, args);
        log.info("RedNote-DataAlign启动完成！");
    }
}
