package com.security.study.spring.Bean生命周期详解.demo1;

import org.springframework.context.annotation.Lazy;

@Lazy
public class Car230611 {
    
    private String name;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
