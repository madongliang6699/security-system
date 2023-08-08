package com.security.study.spring.注解.java注解预备知识.demo1;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR,
        ElementType.TYPE_PARAMETER, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnn1_1 {

    int value();
    

}
