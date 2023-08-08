package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

public class Service1 {
    
    public String say(String name) {
        System.out.println("执行 say 方法。。。。。。");
        return "你好：" + name;
    }
    
}
