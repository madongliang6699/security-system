package com.security.study.spring.注解.Conditional注解.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig0513.class);
        // User0513 bean = context.getBean(User0513.class);//测试可得：如果配置类上加上@Conditional(MyCondition.class)，这里是获取不到bean的，因为配置就没被解析。
        // System.out.println(bean);
    
    
        Map<String, User0513> beansOfType = context.getBeansOfType(User0513.class);//测试可得：如果@Bean上加@Conditional(MyCondition.class)，对应的bean就不会被注册。
        for (String s : beansOfType.keySet()) {
            System.out.println(s+"："+context.getBean(s));
        }
        
    }
}
