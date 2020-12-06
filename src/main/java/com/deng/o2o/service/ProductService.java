package com.deng.o2o.service;

import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ProductExecution;
import com.deng.o2o.entity.Product;
import com.deng.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;


public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException;

    Product getProductById(long productId);

    ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws  ProductOperationException;

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);


}
