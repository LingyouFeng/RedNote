package com.redshop.product.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新商品分类
 *
 * @author Jason Feng
 * @since 2026-11-28 20:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCategoryReqVO {

    @NotNull(message = "商品分类 ID 不能为空")
    private Long id;

    private String name;

    private String image;

    private Integer sort;
}
