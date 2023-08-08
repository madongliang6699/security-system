package com.security.study.spring.注解.PropertySource_Value注解动态刷新.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController_0725 {
    
    @Autowired
    DBModel dbModel;
    
    @RequestMapping("/0725")
    public void aa() {
        System.out.println("---------------" + dbModel);
    }
    
}
