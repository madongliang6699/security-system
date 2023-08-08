package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnvConditional(EnvConditional.Env.TEST)
public class TestBeanConfig  {
    
    
    @Bean
    public String name() {
        return "我是测试环境!";
    }
    
    
    /**
     * 这里可以做一些测试环境的配置初始化
     * 比如测试环境的数据库配置
     */
    
}
