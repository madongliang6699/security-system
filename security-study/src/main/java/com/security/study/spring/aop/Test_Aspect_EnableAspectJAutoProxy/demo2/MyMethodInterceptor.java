package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        System.out.println("我是MethodInterceptor start");
        //调用mi.proceed()执行下一个拦截器
        Object retVal = mi.proceed();
        System.out.println("我是MethodInterceptor end");
        //返回结果
        return retVal;
    }
}