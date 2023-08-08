package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PostProcessBeforeInitializationTest1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringContextUtil.class);
        context.register(Bean1_230715.class);
        context.refresh();
    
        // SpringContextUtil.getBean(xxxxx.class);//工具类可以这样直接使用那些spring的内置对象了
    }
}
