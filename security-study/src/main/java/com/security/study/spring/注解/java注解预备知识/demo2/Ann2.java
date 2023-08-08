package com.security.study.spring.注解.java注解预备知识.demo2;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Ann1
public @interface Ann2 {
    String value() default "b";
    
    @AliasFor(annotation = Ann1.class, value = "value")
    String ann1Value();
}