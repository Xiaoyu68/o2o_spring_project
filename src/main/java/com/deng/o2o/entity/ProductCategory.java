package com.deng.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
    private Long productCategoryId;
    private Shop shopId;
    private String productCategoryName;
    private Integer priority;
    private Date creatTime;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public Shop getShopId() {
        return shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setShopId(Shop shopId) {
        this.shopId = shopId;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
