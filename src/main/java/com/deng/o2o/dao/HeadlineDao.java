package com.deng.o2o.dao;

import com.deng.o2o.entity.Headline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadlineDao {
    List<Headline> queryHeadLine(@Param("headLineCondition") Headline headlineCondition);

}
