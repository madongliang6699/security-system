package com.security.study.Security_JWT.Security_JWT.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ⾃定义登录失败处理
 * <p>
 * 和⾃定义登录成功（MyAuthenticationSuccessHandler）处理⼀样，Spring Security 同样为前后端分离开发提供了登录失败的
 * 处理，这个类就是 AuthenticationFailureHandler，
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 根据接⼝的描述信息,也可以得知登录失败会⾃动回调这个⽅法，进⼀步查看它的默认实现，
     * 你会发现failureUrl、failureForwardUrl也是由它的⼦类实现的。
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     *                  request.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("msg", "登录失败了: " + exception.getMessage());
        result.put("status", 500);
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);

    }
}
