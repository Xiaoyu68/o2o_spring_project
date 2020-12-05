package com.deng.o2o.enums;

import com.deng.o2o.entity.Product;

public enum  ProductStateEnum {
    CHECK(0,"checking"),OFFLINE(-1,"invalid"),
    SUCCESS(1,"success"),PASS(2,"pass"),INNER_ERROR(-1001,"error"),
    NULL_PRODUCTID(-1002,"productId is empty"),NULL_PRODUCT(-1003,"product is empty");
    private int state;
    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    /**
     * 依据传入的state返回相应的值
     */
    public static ProductStateEnum stateOf(int state){
        for(ProductStateEnum stateEnum:values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
