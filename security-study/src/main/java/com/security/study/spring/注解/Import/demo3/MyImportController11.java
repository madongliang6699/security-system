package com.security.study.spring.注解.Import.demo3;

import com.security.study.spring.注解.Import.demo1.MyImportTestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyImportController11 {
    
    @Autowired
    MyImportTestUser myImportTestUser;
    @Autowired
    MyImportTestUser myImportTestUser1;
    
    /**
     * 这里测试可以得出，可以同时注入个MyImportTestUser，只要注入的名字不一样。
     *
     */
    
    
    
    
    @RequestMapping(value = "/ee")
    public String cc(Integer a, String dd) {
        
        String name = myImportTestUser.getName();
        System.out.println(name);
        
        System.out.println(myImportTestUser1.getName());
        
        return "地方";
    }
    
}
