package com.security.study.spring.注解.Import.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyImportController {
    
    @Autowired
    MyImportTestUser myImportTestUser;
    
    @RequestMapping(value = "/cc")
    public String cc(Integer a, String dd) {
        String name = myImportTestUser.getName();
        System.out.println("name:"+name);
        return "地方";
    }
    
}
