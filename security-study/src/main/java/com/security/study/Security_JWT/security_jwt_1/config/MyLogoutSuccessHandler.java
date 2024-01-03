package com.security.study.Security_JWT.security_jwt_1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端分离注销登录配置。
 * <p>
 * 如果是前后端分离开发，注销成功之后就不需要⻚⾯跳转了，只需要将注销成功的信息
 * 返回前端即可，此时我们可以通过⾃定义 LogoutSuccessHandler 实现来返回注销
 * 之后信息：
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = new HashMap();
        result.put("msg", "注销成功");
        result.put("status", 200);
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);
    }
}
