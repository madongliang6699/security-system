package com.security.study.spring.注解.Import.demo1;

public class MyImportTestUser {
    
    private String name;
    private String age;
    
    public String getName() {
        System.out.println("getName........");
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
}
