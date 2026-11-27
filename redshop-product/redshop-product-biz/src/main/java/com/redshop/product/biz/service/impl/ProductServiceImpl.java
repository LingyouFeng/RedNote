package com.redshop.product.biz.service.impl;

import com.rednote.common.response.Result;
import com.redshop.product.biz.domain.dataobject.ProductCategoryDO;
import com.redshop.product.biz.domain.mapper.ProductCategoryDOMapper;
import com.redshop.product.biz.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Jason Feng
 * @since 2026-11-26 22:58
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductCategoryDOMapper categoryDOMapper;

    @Override
    public Result<?> getCategory() {
        List<ProductCategoryDO> categoryDOS = categoryDOMapper.selectAll();
        return null;
    }
}
