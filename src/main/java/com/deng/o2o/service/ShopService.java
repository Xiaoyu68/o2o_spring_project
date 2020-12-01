package com.deng.o2o.service;

import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImg, String fileName);
}
