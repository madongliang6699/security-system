package com.security.study.Security_JWT.security_jwt_2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mySecurityTest")
public class MySecurityTestController {

    @PreAuthorize("hasRole('ROLE_ADM')")
    @Secured("ROLE_PRODUCT")
    @GetMapping("/aa")
    public String aa(){
        System.out.println("-----");
        return "nihao";
    }


}
