package com.security.study.spring.Bean生命周期详解.demo10;

import org.springframework.stereotype.Component;

@Component
public class Service1_230716 {
    
    public Service1_230716() {
        System.out.println("create " + this.getClass());
    }
    
}
