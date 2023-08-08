package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

import org.springframework.aop.ThrowsAdvice;
import java.lang.reflect.Method;

public class MyThrowsAdvice implements ThrowsAdvice {
    
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("我是ThrowsAdvice");
    }
    
}
