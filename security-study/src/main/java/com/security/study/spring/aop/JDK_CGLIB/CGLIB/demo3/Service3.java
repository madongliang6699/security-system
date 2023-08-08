package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo3;

/**
 * 被代理的类，是类，不是接口。
 */
public class Service3 {
    public String m1() {
        System.out.println("我是m1方法");
        return "我是m1返回值";
    }
    public String m2() {
        System.out.println("我是m2方法");
        return "我是m2返回值";
    }
    public Integer m3() {
        System.out.println("我是m3方法");
        return 222;
    }
}
