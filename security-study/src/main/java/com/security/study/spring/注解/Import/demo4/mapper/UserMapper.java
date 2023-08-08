package com.security.study.spring.注解.Import.demo4.mapper;


import com.security.study.spring.注解.Import.demo4.MyMapper;

@MyMapper
public class UserMapper {
    
    public void addUser(){
        System.out.println("add one user......");
    }
}
