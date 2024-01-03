package com.security.study.Security_JWT.security_jwt_2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Robod
 * @date 2020/8/9 17:30
 */
@Data
public class SysRole implements GrantedAuthority {

    private Integer id;
    private String roleName;
    private String roleDesc;

    /**
     * 如果授予的权限可以当作一个String的话，就可以返回一个String
     * @return
     */
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
