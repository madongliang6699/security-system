package com.security.study.Security_JWT.security_jwt_2.config;

import com.security.study.Security_JWT.security_jwt_2.filter.JwtLoginFilter;
import com.security.study.Security_JWT.security_jwt_2.service.MySecurityUserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity //这个注解的意思是这个类是Spring Security的配置类
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MySecurityUserService mySecurityUserService;
    @Resource
    private RsaKeyProperties rsaKeyProperties;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户的来源
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库中
        auth.userDetailsService(mySecurityUserService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置SpringSecurity相关信息
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //关闭csrf
                .addFilter(new JwtLoginFilter(super.authenticationManager(), rsaKeyProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //禁用session
    }

}
