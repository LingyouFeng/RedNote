package com.redshop.product.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品服务启动类
 *
 * @author Jason Feng
 * @since 2026-11-26 22:38
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.redshop")
public class RedShopProductBizApp {

    public static void main(String[] args) {
        log.info("RedShop-Product-Biz开始启动！");
        SpringApplication.run(RedShopProductBizApp.class, args);
        log.info("RedShop-Product-Biz启动完成！");
    }

}
