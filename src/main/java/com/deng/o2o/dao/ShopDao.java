package com.deng.o2o.dao;

import com.deng.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 插入店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
