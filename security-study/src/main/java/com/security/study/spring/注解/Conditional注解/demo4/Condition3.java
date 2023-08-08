package com.security.study.spring.注解.Conditional注解.demo4;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Condition3 implements Condition, PriorityOrdered {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        System.out.println(this.getClass().getName());
        return true;
    }
    
    /**
     * 这种实现PriorityOrdered接口的方式也能指定顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        //todo  需要特别注意的是，PriorityOrdered方式的Condition，无论这里返回的是什么数字，优先级都比其他两种高。
        return 1;
    }
}
