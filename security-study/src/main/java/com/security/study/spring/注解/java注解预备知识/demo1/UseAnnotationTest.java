package com.security.study.spring.注解.java注解预备知识.demo1;

import java.util.Map;

@MyAnn1("用在了类上")
@MyAnn1_1(23)
public class UseAnnotationTest<@MyAnn1("用在了类变量类型V1上") @MyAnn1_1(1) V1, @MyAnn1("用在了类变量类型V2上") @MyAnn1_1(2) V2> {
    
    
    @MyAnn1("用在了字段上")
    @MyAnn1_1(3)
    private String name;
    
    private Map<@MyAnn1("用在了泛型类型上,String") @MyAnn1_1(4) String, @MyAnn1("用在了泛型类型上,Integer") @MyAnn1_1(5) Integer> map;
    
    @MyAnn1("用在了构造方法上")
    @MyAnn1_1(6)
    public UseAnnotationTest(String name){
        this.name = name;
    }
    
    @MyAnn1("用在了方法上")
    @MyAnn1_1(7)
    public String m1(@MyAnn1("用在了参数上") @MyAnn1_1(8) String name) {
        return "mmmmm";
    }
    
}
