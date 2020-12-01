package com.deng.o2o.dao;

import com.deng.o2o.BaseTest;
import com.deng.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {
    /**
     * 列出区域列表
     * @return areaList
     */
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(3,areaList.size());
    }

}
