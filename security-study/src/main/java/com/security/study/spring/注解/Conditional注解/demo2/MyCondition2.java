package com.security.study.spring.注解.Conditional注解.demo2;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;


/**
 * 案例2：bean不存在的时候才注册
 *
 * IService0513 有两个实现类，要求只能注册其中一个作为IService0513的bean
 */
public class MyCondition2 implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //从容器中获取IService类型bean
        Map<String, IService0513> serviceMap = beanFactory.getBeansOfType(IService0513.class);
        //判断serviceMap是否为空
        return serviceMap.isEmpty(); //这里判断IService0513的bean是否已经有注册了。
    }
}
