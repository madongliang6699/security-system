package com.security.study.Security_JWT.security_jwt_2.handler;

import cn.hutool.json.JSONUtil;
import com.security.study.Security_JWT.security_jwt_2.constant.LoginResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证失败处理器
 * AuthenticationFailureHandler 用来解决身份验证失败的异常(适用表单登录方式)
 *
 * @author zhaoxg on 2023年04月18日 10:15
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Value("${loginType}")
    private String loginType;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (LoginResponseType.JSON.name().equals(loginType)) {
            // 认证失败响应JSON字符串，
//            Result result = Result.error(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("msg", exception.getMessage());

            //jwt验证的地方已经做了异常统一处理,这里就不需要了
            //if (exception instanceof LockedException) {
            //    map.put("msg", "账户被锁定，请联系管理员!");
            //} else if (exception instanceof CredentialsExpiredException) {
            //    map.put("msg","密码过期，请联系管理员!");
            //} else if (exception instanceof AccountExpiredException) {
            //    map.put("msg","账户过期，请联系管理员!");
            //} else if (exception instanceof DisabledException) {
            //    map.put("msg","账户被禁用，请联系管理员!");
            //} else if (exception instanceof BadCredentialsException) {
            //    map.put("msg","用户名或者密码输入错误，请重新输入!");
            //}
            //

            String message = JSONUtil.toJsonStr(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(message);
        } else {
            // 重定向回认证页面，注意加上 ?error
            super.setDefaultFailureUrl("/login/page" + "?error");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
