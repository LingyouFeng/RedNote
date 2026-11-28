package com.redshop.product.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jason Feng
 * @since 2026-11-28 21:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCategoryMqDTO {

    private Long id;

    private String name;

    private String image;

    private Integer sort;

    private Long msgVersion;
}
