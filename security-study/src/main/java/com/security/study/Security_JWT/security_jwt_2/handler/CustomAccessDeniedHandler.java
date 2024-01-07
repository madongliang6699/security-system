package com.security.study.Security_JWT.security_jwt_2.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 *
 * @author zhaoxg on 2023年04月18日 10:43
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
//        Result result = Result.error(403, accessDeniedException.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 403);
        map.put("msg", accessDeniedException.getMessage());
        String message = JSONUtil.toJsonStr(map);
        response.getWriter().write(message);
    }
}
