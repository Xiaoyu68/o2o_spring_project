package com.deng.o2o.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {
    private Integer productId;
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
}
