package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class InitMethodTest {
    
    public static void main(String[] args) {
        
        
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition service = BeanDefinitionBuilder.genericBeanDefinition(MyService230716.class).
                setInitMethodName("init"). //@1：指定初始化方法
                        getBeanDefinition();
        factory.registerBeanDefinition("service", service);
        System.out.println(factory.getBean("service"));
        
        //调用顺序：InitializingBean中的afterPropertiesSet、然后再调用指定的自定义的初始化方法
        
        
    }
    
    
}
