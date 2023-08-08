package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;

import org.springframework.beans.factory.annotation.Autowired;

@MyBean
public class UserService0509 {
    
    @Autowired
    User0509 user0509;
    
    public void aa(){
        System.out.println(user0509);
    }
    
}
