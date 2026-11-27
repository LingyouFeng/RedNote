package com.redshop.product.biz.domain.mapper;

import com.redshop.product.biz.domain.dataobject.ProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductCategoryDO record);

    int insertSelective(ProductCategoryDO record);

    ProductCategoryDO selectByPrimaryKey(Long id);

    List<ProductCategoryDO> selectAll();

    int updateByPrimaryKeySelective(ProductCategoryDO record);

    int updateByPrimaryKey(ProductCategoryDO record);
}