package com.deng.o2o.service;


import com.deng.o2o.dto.ProductCategoryExecution;
import com.deng.o2o.entity.ProductCategory;
import com.deng.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询指定某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batechAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}
