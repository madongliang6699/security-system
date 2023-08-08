package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnvConditional(EnvConditional.Env.PROD)
public class ProdBeanConfig {
    
    
    @Bean
    public String name() {
        return "我是成产环境!";
    }
    
    
   
}
