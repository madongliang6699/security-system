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
        return "/all/a1";
    }


    @GetMapping("/all/a2")
    public String alla2(){
        System.out.println("-----");
        return "/all/a2";
    }

    @GetMapping("/one/a1")
    public String onea1(){
        System.out.println("-----");
        return "/one/a1";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured("ROLE_PRODUCT")
    @GetMapping("/admin/a1")
    public String admina1(){
        System.out.println("-----");
        return "/admin/a1";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured("ROLE_PRODUCT")
    @GetMapping("/admin/a2")
    public String admina2(){
        System.out.println("-----");
        return "/admin/a2";
    }


    @GetMapping("/user/a2")
    public String usera2(){
        System.out.println("-----");
        return "/user/a2";
    }

}
