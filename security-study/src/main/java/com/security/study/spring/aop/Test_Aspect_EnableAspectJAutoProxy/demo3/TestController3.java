package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController3 {

    @Autowired
    Service3 service3;
    
    
    @GetMapping("/bb")
    public Integer aa(){
    
        service3.say("小马");
        
        return new Random().nextInt();
    }



}
