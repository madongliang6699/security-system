package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo3;

import org.springframework.stereotype.Service;

@Service
public class Service3 {
    public String say(String name) {
        System.out.println("执行业务方法、、、、、、");
        return "你好：" + name;
    }
}
