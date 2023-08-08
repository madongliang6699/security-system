package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest230715 {
    public static void main(String[] args) {
        // DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // factory.registerSingleton();
        // factory.addBeanPostProcessor();
    
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringContextUtil.class);
        context.register(Bean1_230715.class);
        context.refresh();
    }
}
