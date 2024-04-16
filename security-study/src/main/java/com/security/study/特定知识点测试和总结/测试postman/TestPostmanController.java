package com.security.study.特定知识点测试和总结.测试postman;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/testPostman")
public class TestPostmanController {


    @GetMapping("/test1")
    public String test1(ServletRequest request, String token, Integer appId) {

        String headerToken = request.getParameter("token");
        return headerToken + "_" + token + "_" + appId;

    }
}
