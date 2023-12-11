package com.security.study.Security_JWT.Security_JWT.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.study.Security_JWT.Security_JWT.exception.KaptchaNotMatchException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 在⾃定义LoginKaptchaFilter中加⼊验证码验证。
 *
 *
 *
 * 使用验证码先加入jar包：
 * <!--  kaptcha 是给spring security添加验证码功能的依赖      -->
 *         <dependency>
 *             <groupId>com.github.penggle</groupId>
 *             <artifactId>kaptcha</artifactId>
 *             <version>2.3.2</version>
 *         </dependency>
 *
 *
 */
public class LoginKaptchaFilter extends UsernamePasswordAuthenticationFilter {

    public static final String FORM_KAPTCHA_KEY = "kaptcha";
    private String kaptchaParameter = FORM_KAPTCHA_KEY;

    public String getKaptchaParameter() {
        return kaptchaParameter;
    }

    public void setKaptchaParameter(String kaptchaParameter) {
        this.kaptchaParameter = kaptchaParameter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try {
            //1.获取请求数据
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String kaptcha = userInfo.get(getKaptchaParameter());//⽤来获取数据中验证码
            String username = userInfo.get(getUsernameParameter());//⽤来接收⽤户名
            String password = userInfo.get(getPasswordParameter());//⽤来接收密码

            //2.获取 session 中验证码
            String sessionVerifyCode = (String) request.getSession().getAttribute("kaptcha");
            if (!ObjectUtils.isEmpty(kaptcha) && !ObjectUtils.isEmpty(sessionVerifyCode) && kaptcha.equalsIgnoreCase(sessionVerifyCode)) {
                //3.获取⽤户名 和密码认证
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new KaptchaNotMatchException("验证码不匹配!");
    }

}
