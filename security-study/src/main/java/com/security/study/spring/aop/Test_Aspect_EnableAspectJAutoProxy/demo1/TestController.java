package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {
    
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    
    @GetMapping("/aa")
    public Integer aa(){
        userService.say();
        // carService.say();
    
        // AspectJAfterThrowingAdvice
        // AspectJAfterReturningAdvice
        // AspectJAfterReturningAdvice
        // AspectJAfterAdvice
        // DefaultPointcutAdvisor
    
    
        return new Random().nextInt();
    }
    
    
}
