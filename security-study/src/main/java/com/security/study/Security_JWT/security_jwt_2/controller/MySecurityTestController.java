package com.security.study.Security_JWT.security_jwt_2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySecurityTestController {



    @GetMapping("/all/a1")
    public String alla1(){
        System.out.println("-----");
        return "nihao";
    }


    @GetMapping("/all/a2")
    public String alla2(){
        System.out.println("-----");
        return "nihao";
    }

    @GetMapping("/one/a1")
    public String onea1(){
        System.out.println("-----");
        return "nihao";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured("ROLE_PRODUCT")
    @GetMapping("/admin/a1")
    public String admina1(){
        System.out.println("-----");
        return "nihao";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured("ROLE_PRODUCT")
    @GetMapping("/admin/a2")
    public String admina2(){
        System.out.println("-----");
        return "nihao";
    }


    @GetMapping("/user/a2")
    public String usera2(){
        System.out.println("-----");
        return "nihao";
    }

}
