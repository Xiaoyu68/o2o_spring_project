package com.deng.o2o.service;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.Area;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList() throws JsonProcessingException {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("hunan",areaList.get(0).getAreaName());
    }
}
