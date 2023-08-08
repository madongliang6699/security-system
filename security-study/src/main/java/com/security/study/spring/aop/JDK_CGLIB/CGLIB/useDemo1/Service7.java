package com.security.study.spring.aop.JDK_CGLIB.CGLIB.useDemo1;

/**
 * 被代理的类，是类，不是接口。
 */
public class Service7 {
    public void insert1() {
        System.out.println("我是insert1");
    }
    
    public void insert2() {
        System.out.println("我是insert2");
    }
    
    public String get1() {
        System.out.println("我是get1");
        return "get1";
    }
    
    public String get2() {
        System.out.println("我是get2");
        return "get2";
    }
}
