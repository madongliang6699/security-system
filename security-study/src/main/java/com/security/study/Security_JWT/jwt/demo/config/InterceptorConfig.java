package com.security.study.Security_JWT.jwt.demo.config;

import com.security.study.Security_JWT.jwt.demo.interceptors.JWTInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")         //其他接口token验证
                .excludePathPatterns("/user/login");  //所有用户都放行
    }
}
