package com.security.study.myBatis.A_Mybatis使用详解1.mapper;

import com.security.study.myBatis.A_Mybatis使用详解1.UserModel_0729;

import java.util.List;

public interface UserMapper {
    
    int insertUser(UserModel_0729 model);
    
    int updateUser(UserModel_0729 model);
    
    int deleteUser(Long userId);
    
    List<UserModel_0729> getUserList();
    
    
}
