package com.rednote.user.relation.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户关系服务启动类
 *
 * @author Jason Feng
 * @since 2026-03-08 23:22
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.rednote")
public class RedNoteUserRelationBizApplication {

    public static void main( String[] args ) {
        log.info("RedNote-UserRelation-Biz开始启动！");
        SpringApplication.run(RedNoteUserRelationBizApplication.class, args);
        log.info("RedNote-UserRelation-Biz开始启动！");
    }
}
