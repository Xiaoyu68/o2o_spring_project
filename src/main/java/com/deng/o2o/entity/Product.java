package com.deng.o2o.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {
    private Long productId;
    private ProductCategory productCategory;
    private Shop shop;
    private String productName;
    private String productDesc;
    private ProductImg productImg;
    private Integer priority;
    private String normalPrice;
    private String promotionPrice;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;
    private List<ProductImg> productImgList;

    public Long getProductId() {
        return productId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Shop getShop() {
        return shop;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public ProductImg getProductImg() {
        return productImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductImg(ProductImg productImg) {
        this.productImg = productImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }
}
