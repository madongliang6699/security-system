package com.security.study.Security_JWT.security_jwt_2.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Robod
 * @date 2020/8/9 17:29
 */
@Data
public class SysUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    //指示用户是否被启用或禁用
    private Boolean enabled;
    //指示用户的帐户是否已过期
    private Boolean accountNonExpired;
    //指示用户是否被锁定或解锁
    private Boolean accountNonLocked;
    //指示用户的凭据（密码）是否已过期
    private Boolean credentialsNonExpired;
    //角色
    private List<SysRole> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roleList = new ArrayList<>();
        roles.forEach(role -> roleList.add(new SimpleGrantedAuthority(role.getRoleName())));
        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
