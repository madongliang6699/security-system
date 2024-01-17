package com.security.study.Security_JWT.security_jwt_2.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhaoxinguo
 * @Date: 2018/9/20 14:55
 * @Description: 自定义认证拦截器
 * @desc AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Result result = Result.error(401, authException.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", authException.getMessage());

        if (authException instanceof LockedException) {
            map.put("msg", "账户被锁定:" + authException.getMessage());
        } else if (authException instanceof CredentialsExpiredException) {
            map.put("msg", "密码过期:" + authException.getMessage());
        } else if (authException instanceof NonceExpiredException) {
            map.put("msg", "账户过期:" + authException.getMessage());
        } else if (authException instanceof DisabledException) {
            map.put("msg", "账户被禁用:" + authException.getMessage());
        } else if (authException instanceof BadCredentialsException) {
            map.put("msg", "用户名或者密码输入错误，请重新输入:" + authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            map.put("msg", "身份验证失败, 请确认token信息; 异常信息:" + authException.getMessage());
        }

        String message = JSONUtil.toJsonStr(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
    }

}