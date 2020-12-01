package com.deng.o2o.exceptions;

//意义在于让异常信息更加详细
public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg){
        super(msg);
    }
}
