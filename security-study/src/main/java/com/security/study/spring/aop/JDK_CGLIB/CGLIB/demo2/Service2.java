package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo2;

/**
 * 被代理的类，是类，不是接口。
 */
public class Service2 {
    public void m1() {
        System.out.println("我是m1方法");
        m2(); //todo 注意这里调用了m2方法。
    }
    public void m2() {
        System.out.println("我是m2方法");
    }
}
