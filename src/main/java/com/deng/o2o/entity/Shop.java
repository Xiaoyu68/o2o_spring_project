package com.deng.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {
    private Integer shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddress;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;
    private String advice;
    private Area area;
    private PersonInfo owner;
    private ShopCategory shopCategory;

    public Integer getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getPhone() {
        return phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public Integer getPriority() {
        return priority;
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

    public String getAdvice() {
        return advice;
    }

    public Area getArea() {
        return area;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }
}
