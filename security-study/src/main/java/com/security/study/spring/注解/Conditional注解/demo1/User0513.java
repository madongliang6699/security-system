package com.security.study.spring.注解.Conditional注解.demo1;


import com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1.MyBean;

@MyBean
public class User0513 {
    
    
    private String name = "xiaoming0513";
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User0513{" +
                "name='" + name + '\'' +
                '}';
    }
}
