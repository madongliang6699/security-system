package com.security.study.Security_JWT.security_jwt_2.constant;

/**
 * @author zhaoxg on 2023年03月14日 10:46
 */
public class AuthWhiteList {

    /**
     * 需要放行的URL
     */
    public static final String[] AUTH_WHITELIST = {
            // -- 测试权限放行相关url
            "/all/**",
            "/one/a1",
            // -- 注册用户相关的url
            "/users/signup",
            "/users/addTask",
            "/users/userListV2",
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- 验证码
            "/vc.png"
            // other public endpoints of your API may be appended to this array
    };
}
