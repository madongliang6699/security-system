package com.security.study.特定知识点测试和总结.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/aa")
public class AAController {
    
    @Autowired
    AAService aaService;
    
    @GetMapping(value = "/aa")
    public String aa(){
        aaService.aa();
        return new Random().nextInt() + "sd";
    }
    

}
