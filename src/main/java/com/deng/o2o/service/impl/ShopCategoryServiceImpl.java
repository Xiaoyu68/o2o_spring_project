package com.deng.o2o.service.impl;


import com.deng.o2o.cache.JedisUtil;
import com.deng.o2o.dao.ShopCategoryDao;
import com.deng.o2o.entity.ShopCategory;
import com.deng.o2o.exceptions.ShopOperationException;
import com.deng.o2o.service.ShopCategoryService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SCLISTKEY;

        List<ShopCategory> shopCategoryList = null;

        ObjectMapper mapper = new ObjectMapper();

        if(shopCategoryCondition == null) {
            key = key + "_allfirstlevel";
        } else if(shopCategoryCondition != null && shopCategoryCondition.getParent() != null
        && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            key = key + "_parnet" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if(shopCategoryCondition != null) {
            key = key + "_allsecondlevel";
        }

        if(!jedisKeys.exists(key)) {
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (Exception e){
                e.printStackTrace();
                throw new ShopOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ShopOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
