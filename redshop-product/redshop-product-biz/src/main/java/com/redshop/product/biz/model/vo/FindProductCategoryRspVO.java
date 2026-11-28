package com.redshop.product.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取商品分类
 *
 * @author Jason Feng
 * @since 2026-11-27 22:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindProductCategoryRspVO {

    private Long id;

    private String name;

    private String image;

    private Integer level;

    private Integer sort;

    private Boolean hasChildren;
}
