package com.security.study.spring.aop.aopSourceCode;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class Demo5 {
    
    /**
     * 先来看一段代码，Service类中有2个方法，m1方法中会调用m2，通过aop代理对这个类创建了一个代理，通过代理来统计所有调用方法的耗时
     */
    static class Service {
        public void m1() {
            System.out.println("m1");
            // this.m2();
            ((Service) AopContext.currentProxy()).m2();
        }
        
        public void m2() {
            System.out.println("m2");
        }
    }
    
    
    public static void main(String[] args) {
        /**
         * 案例5：将代理暴露在threadLocal中
         *
         */
        
        
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Service());
        
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                long startTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(String.format("%s方法耗时(纳秒):%s", invocation.getMethod().getName(), endTime - startTime));
                return result;
            }
        });
        
        
        proxyFactory.setExposeProxy(true);
        
        
        Service proxy = (Service) proxyFactory.getProxy();
        proxy.m1();
        
        /**
         *执行结果：
         * m1
         * m2
         * m1方法耗时(纳秒):19070200
         *
         *
         *
         * 为什么没有输出m2方法的耗时?
         * 原因：m2方法是在m1方法中通过this的方式来调用的，this实际上指向的是上面代码中的target对象。
         * 那么我们如何能让此处的m2也能被增强，你需要通过代理来调用m2方法才可以，可以将代理对象暴露在threadLocal中，
         * 然后在m1方法中获取到threadLoca中的代理对象，通过代理对象来调用m2就可以了。
         *
         * 需要调整改动2处:
         * 第1处：配置代理创建时，将其暴露出去:
         * proxyFactory.setExposeProxy(true);
         * 第2处：m1中调用m2的方法需要修改为下面这样:
         * ((Service)AopContext.currentProxy()).m2();
         *
         * 修改之后的执行：
         * m1
         * m2
         * m2方法耗时(纳秒):93899
         * m1方法耗时(纳秒):22798400
         *
         * 其实不就是把代理对象放进了当前线程中的ThreadLocal中，在调用方法的时候，从其中拿到代理对象，还是用这个代理对象调用方法，
         * 这样就可以走到aop的拦截器了。
         */
        
        
    }
    
    
}
