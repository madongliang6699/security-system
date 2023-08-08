package com.security.study.特定知识点测试和总结.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A
 */
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     *
     * @param a aa磁带
     * @param dd 订单
     * @return
     */
    @GetMapping(value = "/getUser12")
    public List<String> health(Integer a, String dd) {

        String aa = "aa";
        aa.intern();
        char[] chars = aa.toCharArray();

        return new ArrayList<String>(){{
            add("aa");
        }};
    }



}
