package com.redshop.product.biz.service;

import com.rednote.common.response.Result;
import com.redshop.product.biz.model.vo.UpdateProductCategoryReqVO;

/**
 *
 * @author Jason Feng
 * @since 2026-11-26 22:57
 */
public interface ProductService {

    Result<?> getTopCategories();

    Result<?> getChildrenCategories(Long id);

    Result<?> updateCategories(UpdateProductCategoryReqVO reqVO);

    void refreshLocalCacheByVersion(Long msgVersion);
}
