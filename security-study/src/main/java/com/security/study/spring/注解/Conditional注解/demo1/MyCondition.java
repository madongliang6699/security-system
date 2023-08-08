package com.security.study.spring.注解.Conditional注解.demo1;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * 案例1：阻止配置类的处理（用在配置类上），或阻止bean注册（用在@Bean直接上）
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //matches方法内部我们可以随意发挥，此处为了演示效果就直接返回false。
        return false; //返回false就导致条件不满足
    }
}
