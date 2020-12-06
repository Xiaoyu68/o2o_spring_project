package com.deng.o2o.service.impl;


import com.deng.o2o.cache.JedisUtil;
import com.deng.o2o.dao.HeadlineDao;
import com.deng.o2o.entity.Headline;
import com.deng.o2o.exceptions.HeadlineOperationException;
import com.deng.o2o.service.HeadlineService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadlineServiceImpl implements HeadlineService {
    @Autowired
    private HeadlineDao headlineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;


    @Override
    @Transactional
    public List<Headline> getHeadlineList(Headline headlineCondition) throws IOException {

        String key = HLLISTKEY;

        List<Headline> headlineList = null;

        ObjectMapper mapper = new ObjectMapper();

        if(headlineCondition != null && headlineCondition.getEnableStatus() != null) {
            key = key + '_' + headlineCondition.getEnableStatus();
        }

        if(!jedisKeys.exists(key)) {
            headlineList = headlineDao.queryHeadLine(headlineCondition);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(headlineList);
            } catch (Exception e) {
                e.printStackTrace();
                throw new HeadlineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Headline.class);
            try {
                headlineList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
                throw new HeadlineOperationException(e.getMessage());
            }
        }
        return headlineList;
    }
}
