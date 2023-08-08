package com.security.study.spring.aop.Test_Aspect_AspectJProxyFactory.demo1;

public class Service1 {
    
    public void m1() {
        System.out.println("我是 m1 方法");
    }
    public void m2() {
        System.out.println(10 / 0);
        System.out.println("我是 m2 方法");
    }
    
}
