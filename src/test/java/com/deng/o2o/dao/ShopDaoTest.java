package com.deng.o2o.dao;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.Area;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    /**
     * 列出区域列表
     * @return areaList
     */
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("test");
        shop.setShopDesc("test");
        shop.setShopAddress("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("pending");
        int effectNum = shopDao.insertShop(shop);
        assertEquals(1,effectNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1);
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        shop.setShopDesc("1");
        shop.setShopAddress("1");
        int effectNum = shopDao.updateShop(shop);
        assertEquals(1,effectNum);
    }
}
