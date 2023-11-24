package com.security.study.jwt.demo.service;

import com.security.study.jwt.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        //模拟数据库中有这个用户xiaoming
        if ("xiaoming".equals(user.getName()) && "123".equals(user.getPassword())) {
            return user;
        }
        throw new RuntimeException("认证失败~~");
    }

}