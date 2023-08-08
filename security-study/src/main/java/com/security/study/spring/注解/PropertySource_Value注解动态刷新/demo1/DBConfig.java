package com.security.study.spring.注解.PropertySource_Value注解动态刷新.demo1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//todo spring注解@Configuration和@Configurable的区别

@Configuration
@PropertySource("/myConfig.properties")
public class DBConfig {
    

}
