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
                .and() //好像是每个
                .authorizeRequests()
                .antMatchers(AuthWhiteList.AUTH_WHITELIST).permitAll() //给这些url放行, 这些地址能随意请求不需登录; permitAll()就是登不登录都能访问; anonymous():只允许匿名访问,登录后就不能访问;
                .antMatchers("/admin/**").hasRole("ADMIN") //采用了 Ant 风格的路径匹配符 配置权限:admin/路径下的url需要有admin角色的用户才能访问;
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()  // 所有请求需要身份认证; authenticated()就是需要认证的意思;
                //mdl 注意 上面需要注意的是: anyRequest()必须在antMatchers()后面,否则启动就报错; springSecurity这里的设计原则是先匹配上的设置就直接生效,后面的匹配就不起作用了; 并且一般都是先指定放行的部分url,再控制其他所有url需要认证; 这样的设计是合理的;
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
        //临时添加内存用户
        //auth.inMemoryAuthentication()
        //        .withUser("xiaoming")
        //        .password("123")
        //        .roles("admin")
        //        .and()
        //        .withUser("江南一点雨")
        //        .password("123")
        //        .roles("user")
        //;
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
