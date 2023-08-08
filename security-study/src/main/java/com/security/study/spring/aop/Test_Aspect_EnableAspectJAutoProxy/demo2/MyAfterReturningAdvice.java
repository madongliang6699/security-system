package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

import org.springframework.aop.AfterReturningAdvice;
import java.lang.reflect.Method;

public class MyAfterReturningAdvice  implements AfterReturningAdvice {
    
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("我是AfterReturningAdvice");
    }
}
