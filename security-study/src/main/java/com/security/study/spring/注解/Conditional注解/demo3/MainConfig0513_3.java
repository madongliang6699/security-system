package com.security.study.spring.注解.Conditional注解.demo3;

import org.springframework.context.annotation.Import;

@Import({DevBeanConfig.class, TestBeanConfig.class, ProdBeanConfig.class})
public class MainConfig0513_3 {
}
