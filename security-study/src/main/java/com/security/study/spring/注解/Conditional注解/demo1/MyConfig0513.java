package com.security.study.spring.注解.Conditional注解.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


// @Conditional(MyCondition.class)
@Configuration
public class MyConfig0513 {
    
    @Conditional(MyCondition.class)
    @Bean
    public User0513 user0513(){
        User0513 user0513 = new User0513();
        user0513.setName("111");
        return user0513;
    }
    
    @Bean
    public User0513 user0513_1(){
        User0513 user0513 = new User0513();
        user0513.setName("222");
        return user0513;
    }
    
}
