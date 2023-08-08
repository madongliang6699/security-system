package com.security.study.spring.Bean生命周期详解.demo6;

public class Person230709 {
    
    private String name;
    private Integer age;
    
    public Person230709() {
        System.out.println("调用 Person230709()");
    }
    
    @MyAutowried
    public Person230709(String name) {
        System.out.println("调用 Person230709(String name)");
        this.name = name;
    }
    
    public Person230709(String name, Integer age) {
        System.out.println("调用 Person230709(String name, int age)");
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person230709{" +
                "name=" + name  +
                ", age=" + age +
                '}';
    }
}
