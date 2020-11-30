package com.deng.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Area {
    //area id
    private Integer areaId;
    //name
    private String areaName;
    //weight
    private  Integer priority;
    //createTime
    private Date createTime;
    //edit time
    private Date lastEditTime;

    public String getAreaName() {
        return areaName;
    }
}
