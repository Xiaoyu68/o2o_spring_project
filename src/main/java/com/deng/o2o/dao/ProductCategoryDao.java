package com.deng.o2o.dao;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 通过shop id查询店铺商品类别
     * @param shopId
     * @return
     */
    List<ProductCategoryDao> queryProductCategoryList(long shopId);
}
