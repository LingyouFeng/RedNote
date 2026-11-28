package com.redshop.product.biz.controller;

import com.rednote.common.response.Result;
import com.redshop.product.biz.model.vo.UpdateProductCategoryReqVO;
import com.redshop.product.biz.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/categories/top")
    public Result<?> getTopCategories() {
        return productService.getTopCategories();
    }

    @GetMapping(value = "/categories/children/{id}")
    public Result<?> getChildrenCategories(@PathVariable Long id) {
        return productService.getChildrenCategories(id);
    }

    @PostMapping(value = "/categories/update")
    public Result<?> updateCategories(@RequestBody @Valid UpdateProductCategoryReqVO reqVO) {
        return productService.updateCategories(reqVO);
    }
}
