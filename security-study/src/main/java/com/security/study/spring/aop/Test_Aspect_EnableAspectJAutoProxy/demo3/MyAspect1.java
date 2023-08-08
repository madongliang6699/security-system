package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MyAspect1 {
    
    @Pointcut("execution(* com.mdl.aop.Test_Aspect_EnableAspectJAutoProxy.demo3.Service3.*(..))")
    public void pc() {
    }
    
    @Before("pc()")
    public void before() {
        System.out.println("MyAspect1 @Before通知!");
    }
    @Around("pc()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("MyAspect1 @Around通知start");
        Object result = joinPoint.proceed();
        System.out.println("MyAspect1 @Around绕通知end");
        return result;
    }
    @After("pc()")
    public void after() throws Throwable {
        System.out.println("MyAspect1 @After通知!");
    }
    @AfterReturning("pc()")
    public void afterReturning() throws Throwable {
        System.out.println("MyAspect1 @AfterReturning通知!");
    }
    @AfterThrowing("pc()")
    public void afterThrowing() {
        System.out.println("MyAspect1 @AfterThrowing通知!");
    }
    
}
