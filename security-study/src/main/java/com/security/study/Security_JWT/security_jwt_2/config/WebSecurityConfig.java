package com.security.study.Security_JWT.security_jwt_2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.study.Security_JWT.security_jwt_2.filter.JwtLoginFilter;
import com.security.study.Security_JWT.security_jwt_2.service.MySecurityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, ex) -> { //前后端分离的项目拦截请求之后不应该跳转到某个页面，应该给前端返回一个提示认证的json数据就行了，可以这样设置。
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("msg", "必须认证之后才能访问, " + ex.getMessage());
                    String s = new ObjectMapper().writeValueAsString(result);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().println(s);
                });

        http.csrf().disable()  //关闭csrf
                .addFilter(new JwtLoginFilter(super.authenticationManager(), rsaKeyProperties))
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//禁用session
        ;
    }

}
