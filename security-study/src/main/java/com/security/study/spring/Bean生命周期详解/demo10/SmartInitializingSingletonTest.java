package com.security.study.spring.Bean生命周期详解.demo10;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
/**
 * 所有bean初始化完毕，容器会回调{@link SmartInitializingSingleton#afterSingletonsInstantiated()}
 */
@ComponentScan
public class SmartInitializingSingletonTest {
    public static void main(String[] args) throws InterruptedException {
    
        // 案例1：ApplicationContext自动回调SmartInitializingSingleton接口
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SmartInitializingSingletonTest.class);
        System.out.println("开始启动容器!");
        context.refresh();
        
        Thread.sleep(1000);
        System.out.println("容器启动完毕!");
    
        /**
         * 开始启动容器!
         * create class com.mdl.Bean生命周期详解.demo10.Service1_230716
         * create class com.mdl.Bean生命周期详解.demo10.Service2_230716
         * 所有bean初始化完毕！
         * 容器启动完毕!
         */
    }
}
