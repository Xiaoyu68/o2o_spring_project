package com.deng.o2o.dto;

import com.deng.o2o.entity.Product;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.enums.ProductStateEnum;
import com.deng.o2o.enums.ShopStateEnum;

import java.util.List;

public class ProductExecution {
    private int state;
    //状态标志
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的shop(增删改店铺的时候用)
    private Product product;
    //shop列表
    private List<Product> productList;

    public ProductExecution(){

    }

    //操作失败的构造器
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, Product product){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    //成功的构造器
    public ProductExecution(ProductStateEnum stateEnum, List<Product> productList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }
}
