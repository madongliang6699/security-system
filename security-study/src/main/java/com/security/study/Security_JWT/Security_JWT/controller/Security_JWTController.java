package com.security.study.Security_JWT.Security_JWT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Security_JWTController {

    @GetMapping("nihao")
    public String test() {
        return "buhao";
    }
}
