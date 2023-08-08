package com.security.study.spring.aop.JDK_CGLIB.JDK.pojo;

public class IServiceB implements IService{
    @Override
    public void m1() {
        System.out.println("我是 IServiceB m1 方法");
    }

    @Override
    public void m2() {
        System.out.println("我是 IServiceB m2 方法");
    }

    @Override
    public void m3() {
        System.out.println("我是 IServiceB m3 方法");
    }
}
