package com.deng.o2o.dao;

import com.deng.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 根据商品id获取商品信息
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    int updateProduct(Product product);

    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    int queryProductCount(@Param("productCondition") Product productCondition);

    int updateProductCategoryToNull(long productCategoryId);
}
