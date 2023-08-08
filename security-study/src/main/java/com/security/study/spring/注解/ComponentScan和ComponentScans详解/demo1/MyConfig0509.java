package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        // useDefaultFilters = false, //不启用默认过滤器
        includeFilters = {
                //这个是包含指定注解的类就会被注册到容器
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyBean.class)
                
                // 这个是包含指定类型的类，就会被注册到容器，被扫描的类满足IService.class.isAssignableFrom(被扫描的类)条件的都会被注册到spring容器中
                // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = IService.class)
                
                //还可以自定义过滤器，这个先不总结了，需要的话可以看原文的案例。
        }
    )
public class MyConfig0509 {


}
