package com.security.study.spring.注解.Conditional注解.demo4;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional({Condition1.class, Condition2.class, Condition3.class})//@5
public class MainConfig6 {
}
