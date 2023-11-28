package com.security.multisupport.multipojo.aop;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiHandle {
    boolean value() default true;

    boolean tokenValid() default true;

    boolean customerResponse() default false;

    boolean customerRequest() default false;
}
