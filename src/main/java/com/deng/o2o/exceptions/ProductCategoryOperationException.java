package com.deng.o2o.exceptions;

//意义在于让异常信息更加详细
public class ProductCategoryOperationException extends RuntimeException {
    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}

