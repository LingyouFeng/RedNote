package com.redshop.product.biz.controller;

import com.rednote.common.response.Result;
import com.redshop.product.biz.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品控制器类
 *
 * @author Jason Feng
 * @since 2026-11-26 22:55
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(value = "/category")
    private Result<?> getCategory() {
        return productService.getCategory();
    }
}
