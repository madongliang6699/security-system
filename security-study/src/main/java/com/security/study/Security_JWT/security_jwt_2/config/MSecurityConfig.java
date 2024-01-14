package com.security.study.Security_JWT.security_jwt_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class MSecurityConfig {

    /**
     * 配置角色继承.
     *
     * 但是一般企业中不这么使用.应该是在数据库中配置角色和资源和菜单等之间的关系.
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        //表示 ROLE_admin 自动具备 ROLE_user 的权限
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

}
