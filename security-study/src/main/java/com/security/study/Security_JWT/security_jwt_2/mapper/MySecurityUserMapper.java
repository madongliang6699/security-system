package com.security.study.Security_JWT.security_jwt_2.mapper;

import com.security.study.Security_JWT.security_jwt_2.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 */
@Mapper
public interface MySecurityUserMapper {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.security.study.Security_JWT.security_jwt_2.mapper.MySecurityRoleMapper.findByUid"))
    })
    SysUser findByUsername(String username);
}
