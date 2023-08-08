package com.security.study.spring.注解.Conditional注解.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig0513_2 {
    
    /**
     *下面两个bean注册使用了@Conditional(MyCondition2.class) 就会只注册其中一个bean。
     *
     * 当然，通常某个类型的bean注册不会是下面这样写在一个配置类中，往往是在不同的业务中的配置类中，不确定该类型是否已经注册了bean，
     * 可以使用这个方法避免注册了两个。
     */
    
    @Conditional(MyCondition2.class)
    @Bean
    public IService0513 iService0513_2(){
        IService0513 iService0513 = new Service0513_2();
        return iService0513;
    }
    
    @Conditional(MyCondition2.class)
    @Bean
    public IService0513 iService0513_1(){
        IService0513 iService0513 = new Service0513_1();
        return iService0513;
    }
    
    
    
    
}
