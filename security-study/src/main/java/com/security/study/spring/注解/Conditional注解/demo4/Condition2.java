package com.security.study.spring.注解.Conditional注解.demo4;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Condition2 implements Condition, Ordered {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println(this.getClass().getName());
        return true;
    }
    
    /**
     * 这种实现Ordered接口的方式也能指定顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
