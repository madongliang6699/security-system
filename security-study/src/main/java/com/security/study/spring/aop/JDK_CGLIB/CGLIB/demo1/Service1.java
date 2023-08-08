package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo1;

/**
 * 被代理的类，是类，不是接口。
 */
public class Service1 {
    public void m1() {
        System.out.println("我是m1方法");
    }
    public void m2() {
        System.out.println("我是m2方法");
    }
}
