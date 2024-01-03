package com.security.study.Security_JWT.security_jwt_2.service.impl;


import com.security.study.Security_JWT.security_jwt_2.entity.SysUser;
import com.security.study.Security_JWT.security_jwt_2.mapper.MySecurityUserMapper;
import com.security.study.Security_JWT.security_jwt_2.service.MySecurityUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Robod
 * @date 2020/8/9 17:48
 */
@Service
public class MySecurityUserServiceImpl implements MySecurityUserService {

    @Resource
    private MySecurityUserMapper mySecurityUserMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = mySecurityUserMapper.findByUsername(username);
        return sysUser;
    }

}
