package com.deng.o2o.dao;

import com.deng.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {

    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    int insertLocalAuth(LocalAuth localAuth);

    int updateLocalAuth(@Param("userId") Long userId, @Param("usernam") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
