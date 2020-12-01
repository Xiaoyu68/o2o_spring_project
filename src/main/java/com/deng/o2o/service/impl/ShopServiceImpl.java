package com.deng.o2o.service.impl;

import com.deng.o2o.dao.ShopDao;
import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.enums.ShopStateEnum;
import com.deng.o2o.exceptions.ShopOperationException;
import com.deng.o2o.service.ShopService;
import com.deng.o2o.util.ImageUtil;
import com.deng.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
//runtime exception时，事务可以回滚
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImg, String fileName) {
        //空值判断
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectivedNum = shopDao.insertShop(shop);
            if(effectivedNum <= 0){
                throw new ShopOperationException("Created failed!");
            } else {
                if(shopImg != null){
                    //存储图片 java基本类型是值传递
                    try {
                        addShopImg(shop, shopImg,fileName);
                    } catch (Exception e){
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effectivedNum = shopDao.updateShop(shop);
                    if(effectivedNum <= 0){
                        throw new ShopOperationException("updated image address failed");
                    }
                }
            }
        } catch (Exception e){
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop,InputStream shopImg,String fileName){
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
