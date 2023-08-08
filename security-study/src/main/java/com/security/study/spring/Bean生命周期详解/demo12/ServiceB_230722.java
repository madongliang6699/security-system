package com.security.study.spring.Bean生命周期详解.demo12;

import javax.annotation.PreDestroy;

public class ServiceB_230722 {
    
    public ServiceB_230722() {
        System.out.println("create " + this.getClass());
    }
    
    @PreDestroy
    public void preDestroy() { //@1
        System.out.println("preDestroy()");
    }
    
}
