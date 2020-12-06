package com.deng.o2o.service;

import com.deng.o2o.entity.Area;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AreaService {

    public static final String AREALISTKEY = "arealist";

    List<Area> getAreaList() throws JsonProcessingException;
}
