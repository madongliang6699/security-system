package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
    
    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("我是MethodBeforeAdvice");
    }
}