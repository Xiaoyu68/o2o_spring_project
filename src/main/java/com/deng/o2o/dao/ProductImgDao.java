package com.deng.o2o.dao;

import com.deng.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImgByProductId(long productId);
}
