package com.security.study.spring.Bean生命周期详解.demo12;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class ServiceC_230722 implements DisposableBean {
    
    public ServiceC_230722() {
        System.out.println("创建ServiceC实例");
    }
    
    @PreDestroy
    public void preDestroy1() {
        System.out.println("preDestroy1()");
    }
    
    @PreDestroy
    public void preDestroy2() {
        System.out.println("preDestroy2()");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean接口中的destroy()");
    }
    
    //自定义的销毁方法
    public void customDestroyMethod() { // 这个destroyMethod我们一会通过@Bean注解的方式，将其指定为自定义方法。
        System.out.println("我是自定义的销毁方法:customDestroyMethod()");
    }

    
}
