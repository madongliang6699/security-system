package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        
        // 待写一个自己的要实现的功能。
    
        
        
        return null;
    }
    
}
