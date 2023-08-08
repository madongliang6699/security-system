package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgService0509 {
    
    @Autowired
    UserService0509 userService0509;
    
    public void aaa(){
        userService0509.aa();
    }
    
}
