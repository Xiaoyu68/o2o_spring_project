package com.deng.o2o.service;

import com.deng.o2o.BaseTest;
import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Area;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.entity.ShopCategory;
import com.deng.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("test13");
        shop.setShopDesc("test13");
        shop.setShopAddress("test13");
        shop.setPhone("test13");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("pending");
        File shopImg = new File("");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
}
