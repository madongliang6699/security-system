package com.security.study.spring.aop.Test_Aspect_AspectJProxyFactory.demo1;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class Demo1 {
    public static void main(String[] args) {
        
        Service1 service1 = new Service1();
    
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory();
        aspectJProxyFactory.setTarget(service1);
        //设置标注了@Aspect注解的类
        aspectJProxyFactory.addAspect(Aspect1.class);
    
        Service1 service1Proxy = aspectJProxyFactory.getProxy();
        
        service1Proxy.m1();
        service1Proxy.m2();
        
    
    }
}
