package com.security.study.Security_JWT.security_jwt_1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ⾃定义登录成功处理
 * <p></>
 * 有时候⻚⾯跳转并不能满⾜我们，特别是在前后端分离开发中就不需要成功之后跳转⻚⾯。
 * 只需要给前端返回⼀个 JSON 通知登录成功还是失败与否。
 * 这个时候可以通过⾃定义 AuthenticationSucccessHandler 实现 登录成功后的逻辑。
 * 同理登录失败后自定义 AuthenticationFailureHandler 实现。
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 根据 AuthenticationSuccessHandler 接⼝的描述信息,也可以得知登录成功会⾃动回调这个⽅法，进⼀步查看它的默认实现，
     * 你会发现successForwardUrl、defaultSuccessUrl也是由它的⼦类实现的.
     * <p>
     * 我们来⾃定义 AuthenticationSuccessHandler 这个实现。
     *
     * @param httpServletRequest  the request which caused the successful authentication
     * @param httpServletResponse the response
     * @param authentication      the <tt>Authentication</tt> object which was created during
     *                            the authentication process.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        /**
         * 这里配置完毕之后，要把这个自定义的AuthenticationSuccessHandler配置到security的配置类（前面写的WebSecurityConfigurer）中。
         */

        Map<String, Object> result = new HashMap();
        result.put("msg", "登录成功了啊");
        result.put("status", 200);
        result.put("authentication", authentication);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        httpServletResponse.getWriter().println(s);

    }
}
