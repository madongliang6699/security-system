package com.security.study.spring.autowire.aa;

import org.springframework.stereotype.Service;

@Service
public class UserService0424_2 extends BaseUserService0424 implements IUserService0424{
    
    private String name = "xiaoming";
    private String age = "23";
    
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
        return "UserService0424_2{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
