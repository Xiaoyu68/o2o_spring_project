package com.deng.o2o.enums;

public enum ProductCategoryStateEnum {
    SUCCESS(1,"success"),
    INNER_ERROR(-1001,"error"),EMPTY_LIST(-1002,"list is empty");
    private int state;
    private String stateInfo;

    private ProductCategoryStateEnum(int state, String stateInfo){
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
    public static ProductCategoryStateEnum stateOf(int state){
        for(ProductCategoryStateEnum stateEnum:values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
