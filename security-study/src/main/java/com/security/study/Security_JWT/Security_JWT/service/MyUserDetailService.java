package com.security.study.Security_JWT.Security_JWT.service;

import com.security.study.Security_JWT.Security_JWT.domain.entity.MyUser;
import com.security.study.Security_JWT.Security_JWT.domain.mapper.UserDao;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 这里实现pring security的UserDetailsService接口，自动替换默认的认证数据实现类。
 * 进而实现认证数据源从我们数据库里查询。
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userDao.selectUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("⽤户不存在");
        }
        user.setRoles(userDao.getRolesByUid(user.getId()));
        return user;
    }
}
