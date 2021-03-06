package com.deng.o2o.dao;

import com.deng.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域Id,owner
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /*
    返回总数
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
    /**
     * 通过shop id查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);
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
