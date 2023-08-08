package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnvConditional(EnvConditional.Env.DEV)
public class DevBeanConfig {
    
    
    @Bean
    public String name() {
        return "我是开发环境!";
    }
    
    
    /**
     * 这里可以做一些开发环境的配置初始化
     * 比如开发环境的数据库配置
     */
    
}
