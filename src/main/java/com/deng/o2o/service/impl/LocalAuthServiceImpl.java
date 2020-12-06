package com.deng.o2o.service.impl;

import com.deng.o2o.dao.LocalAuthDao;
import com.deng.o2o.dto.LocalAuthExecution;
import com.deng.o2o.entity.LocalAuth;
import com.deng.o2o.enums.LocalAuthStateEnum;
import com.deng.o2o.exceptions.LocalAuthOperationException;
import com.deng.o2o.service.LocalAuthService;
import com.deng.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        if(localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
        || localAuth.getPersonInfo() == null ||localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_Auth);
        }
        LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if(tempAuth != null) {
            return new LocalAuthExecution(LocalAuthStateEnum.OFFLINE);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if(effectedNum <= 0) {
                throw new LocalAuthOperationException("failded");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        if(userId != null && username != null && password != null && newPassword != null && !newPassword.equals(password)) {
            try {
                int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password), MD5.getMd5(newPassword),new Date());
                if(effectedNum <= 0) {
                    throw new LocalAuthOperationException("failed");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException(e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.INNER_ERROR);
        }
    }
}
