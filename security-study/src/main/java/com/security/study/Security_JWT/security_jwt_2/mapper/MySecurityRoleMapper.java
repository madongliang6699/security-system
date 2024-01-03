package com.security.study.Security_JWT.security_jwt_2.mapper;

import com.security.study.Security_JWT.security_jwt_2.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Robod
 * @date 2020/8/9 17:43
 */
@Mapper
public interface MySecurityRoleMapper {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc " +
            "FROM sys_role r, sys_user_role ur " +
            "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);

}
