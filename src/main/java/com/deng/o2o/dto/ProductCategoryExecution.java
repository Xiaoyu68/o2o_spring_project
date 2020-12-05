package com.deng.o2o.dto;

import com.deng.o2o.entity.Product;
import com.deng.o2o.entity.ProductCategory;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.enums.ProductCategoryStateEnum;
import com.deng.o2o.enums.ShopStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标志
    private String stateInfo;
    //shop列表
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){

    }

    //操作失败的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public ProductCategoryExecution(ShopStateEnum stateEnum, List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
