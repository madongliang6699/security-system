package com.security.study.spring.aop.JDK_CGLIB.JDK.pojo;

public class IServiceA implements IService{
    @Override
    public void m1() {
        System.out.println("我是 IServiceA m1 方法");
    }

    @Override
    public void m2() {
        System.out.println("我是 IServiceA m2 方法");
    }

    @Override
    public void m3() {
        System.out.println("我是 IServiceA m3 方法");
    }
}
