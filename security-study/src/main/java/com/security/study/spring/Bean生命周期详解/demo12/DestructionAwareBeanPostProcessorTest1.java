package com.security.study.spring.Bean生命周期详解.demo12;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class DestructionAwareBeanPostProcessorTest1 {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        //将CommonAnnotationBeanPostProcessor加入BeanPostProcessor列表，当然，如果是工作中的spring环境，这些都是自动会加进去的
        factory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
    
        //这里把自定义的也加进去试试
        // factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        
        factory.registerBeanDefinition("serviceB", BeanDefinitionBuilder.genericBeanDefinition(ServiceB_230722.class).getBeanDefinition());
        //触发所有单例bean初始化
        factory.preInstantiateSingletons();
        System.out.println("销毁serviceB");
        //销毁指定的bean
        factory.destroySingleton("serviceB");
    
        /**
         * 打印：
         * create class com.mdl.Bean生命周期详解.demo12.ServiceB_230722
         * 销毁serviceB
         * preDestroy()
         */
    
        /**
         * 上面把自定义的MyDestructionAwareBeanPostProcessor也加进去后打印：
         *
         * create class com.mdl.Bean生命周期详解.demo12.ServiceB_230722
         * 销毁serviceB
         * preDestroy()
         * 准备销毁bean：serviceB
         *
         * 因为CommonAnnotationBeanPostProcessor 和 MyDestructionAwareBeanPostProcessor 两个类的原理是一样的，都是实现DestructionAwareBeanPostProcessor接口，
         * 所以这里从打印结果上看，好像是谁先被加紧列表，谁的逻辑先执行。至于这个执行顺序能不能控制，后面再研究，好像也没必要同时使用多个DestructionAwareBeanPostProcessor实现类。
         */
        
        
    }
}
