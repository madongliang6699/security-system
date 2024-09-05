package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
        System.out.println("=========================================================");
        System.out.println("============ 启动 成功 ===================");
        System.out.println("=========================================================");
    }

}
