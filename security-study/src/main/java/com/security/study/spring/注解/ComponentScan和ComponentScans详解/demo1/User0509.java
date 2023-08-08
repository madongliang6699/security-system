package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;

@MyBean
public class User0509 {
    
    
    private String name = "xiaoming";
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User0509{" +
                "name='" + name + '\'' +
                '}';
    }
}
