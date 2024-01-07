package com.security.study.Security_JWT.security_jwt_2.config;

import com.security.study.Security_JWT.security_jwt_2.constant.AuthWhiteList;
import com.security.study.Security_JWT.security_jwt_2.filter.JWTAuthenticationFilter;
import com.security.study.Security_JWT.security_jwt_2.filter.JwtLoginFilter;
import com.security.study.Security_JWT.security_jwt_2.handler.CustomAccessDeniedHandler;
import com.security.study.Security_JWT.security_jwt_2.handler.CustomAuthenticationEntryPoint;
import com.security.study.Security_JWT.security_jwt_2.handler.CustomAuthenticationFailureHandler;
import com.security.study.Security_JWT.security_jwt_2.handler.CustomAuthenticationSuccessHandler;
import com.security.study.Security_JWT.security_jwt_2.service.MySecurityUserService;
import com.security.study.Security_JWT.security_jwt_2.service.impl.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity //这个注解的意思是这个类是Spring Security的配置类
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MySecurityUserService mySecurityUserService;
    @Resource
    private RsaKeyProperties rsaKeyProperties;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer = http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//既然使用JWT了就禁用session
                .and()
                .authorizeRequests()
                .antMatchers(AuthWhiteList.AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()  // 所有请求需要身份认证
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager(), rsaKeyProperties))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), rsaKeyProperties))
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())// 自定义身份验证入口点
                .accessDeniedHandler(accessDeniedHandler()) // 自定义访问失败处理器
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())// 认证成功处理器
                .failureHandler(authenticationFailureHandler())// 认证失败处理器
                .and()
                .logout() // 默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")// 设置注销成功后跳转页面，默认是跳转到登录页面;
                .permitAll();
    }

    // 该方法是登录的时候会进入
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(mySecurityUserService, passwordEncoder()));
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


}
