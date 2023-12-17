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
 * ⾃定义 LoginKaptchaFilter 做验证码验证的过滤器。在该过滤器中加⼊验证码验证的逻辑。
 * <p>
 * 说明：使用该验证码的前提是引入其jar包：kaptcha （是给spring security添加验证码功能的依赖）
 * <dependency>
 * <groupId>com.github.penggle</groupId>
 * <artifactId>kaptcha</artifactId>
 * <version>2.3.2</version>
 * </dependency>
 * <p>
 * 该过滤器继承了 security 的 UsernamePasswordAuthenticationFilter 用户认证过滤器，通过内部源码可以得知，
 * 继承后就相当于替换掉了 UsernamePasswordAuthenticationFilter。
 * 然后重写 attemptAuthentication 认证方法，加入验证码验证的逻辑，如果验证码通过，
 * 就继续模仿父类UsernamePasswordAuthenticationFilter中attemptAuthentication方法的逻辑，调用 security 框架自己的逻辑做用户名和密码的验证。
 */
public class LoginKaptchaFilter extends UsernamePasswordAuthenticationFilter {

    //前端传验证码的参数
    public static final String VERIFICATION_CODE = "verificationCode";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        try {
            //1.获取请求数据
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String verificationCode = userInfo.get(VERIFICATION_CODE);//获取前端请求参数中的验证码
            String username = userInfo.get(getUsernameParameter());//获取⽤户名
            String password = userInfo.get(getPasswordParameter());//获取密码

            if(ObjectUtils.isEmpty(verificationCode)){
//                throw new KaptchaNotMatchException("验证码为空!");
            }

            //2.获取 session 中验证码
            String sessionVerifyCode = (String) request.getSession().getAttribute(VERIFICATION_CODE);
            if(ObjectUtils.isEmpty(sessionVerifyCode)){
//                throw new KaptchaNotMatchException("未生成验证码!");
            }

            //3.校验验证码，如果验证码通过，就继续执行用户名和密码的验证（这里先注释掉验证码功能方便后面测试）
//            if (!ObjectUtils.isEmpty(verificationCode) && !ObjectUtils.isEmpty(sessionVerifyCode) && verificationCode.equalsIgnoreCase(sessionVerifyCode)) {
                //3.获取⽤户名和密码认证
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //这里必须使用继承了 AuthenticationException 异常类的自定义异常类，
        // 如果只是用RuntimeException异常，security不做特殊处理，进不到设置好的AuthenticationFailureHandler里。
        throw new KaptchaNotMatchException("验证码不匹配!");
    }

}
