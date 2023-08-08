package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo1;

import org.springframework.stereotype.Component;

@Component
public class CarService {
    public void say(){
        System.out.println("我是CarService");
    }
}
