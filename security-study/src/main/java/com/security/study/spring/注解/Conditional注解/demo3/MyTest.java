package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig0513_3.class);
        System.out.println(context.getBean("name"));
        
    }
}
