package com.deng.o2o.enums;

public enum ShopStateEnum {
    CHECK(0,"checking"),OFFLINE(-1,"invalid"),SUCCESS(1,"success"),PASS(2,"pass"),
    INNER_ERROR(-1001,"error"),NULL_SHOPID(-1002,"ShopId is empty"),NULL_SHOP(-1003,"Shop is empty");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo){
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
    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum:values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
