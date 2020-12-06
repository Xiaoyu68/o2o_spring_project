package com.deng.o2o.service;

import com.deng.o2o.entity.Headline;

import java.io.IOException;
import java.util.List;

public interface HeadlineService {

    public static final String HLLISTKEY = "headlinelist";


    List<Headline> getHeadlineList(Headline headlineCondition) throws IOException;
}
