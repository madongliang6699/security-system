package com.security.study.spring.aop.aopSourceCode.testData;

public class Service1 implements IService1 {
    @Override
    public void say(String name) {
        System.out.println("hello: " + name);
    }
}
