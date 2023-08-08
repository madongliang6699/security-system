package com.security.study.spring.注解.Conditional注解.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig0513_2.class);
        Map<String, IService0513> serviceMap = context.getBeansOfType(IService0513.class);
        serviceMap.forEach((beanName, bean) -> {
            System.out.println(String.format("%s->%s", beanName, bean));
        });
        
    }
}
