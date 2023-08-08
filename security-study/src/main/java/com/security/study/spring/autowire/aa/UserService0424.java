package com.security.study.spring.autowire.aa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// @Primary
@Service
@Primary
public class UserService0424 extends BaseUserService0424 implements IUserService0424{
    
    private String name = "ma";
    private String age;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "User0424{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
