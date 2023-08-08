package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.beans.factory.InitializingBean;

public class MyService230716 implements InitializingBean {
    
    public void init() {
        System.out.println("调用init()方法");
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    
        System.out.println("调用afterPropertiesSet()");
    
    }
}
