package com.redshop.product.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * product_category表实体类
 *
 * @author Jason Feng
 * @since 2026-11-26 22:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDO {

    private Long id;

    private Long parentId;

    private String name;

    private String image;

    private Integer level;

    private Integer sort;

    private Integer status;

    private Integer isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}