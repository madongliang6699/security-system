package com.security.study.spring.Bean生命周期详解.demo12;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class DestructionAwareBeanPostProcessorTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //添加自定义的DestructionAwareBeanPostProcessor
        factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //向容器中注入3个单例bean
        factory.registerBeanDefinition("serviceA1", BeanDefinitionBuilder.genericBeanDefinition(ServiceA_230722.class).getBeanDefinition());
        factory.registerBeanDefinition("serviceA2", BeanDefinitionBuilder.genericBeanDefinition(ServiceA_230722.class).getBeanDefinition());
        factory.registerBeanDefinition("serviceA3", BeanDefinitionBuilder.genericBeanDefinition(ServiceA_230722.class).getBeanDefinition());
        //触发所有单例bean初始化
        factory.preInstantiateSingletons(); //@1
        System.out.println("销毁serviceA1");
        //销毁指定的bean
        factory.destroySingleton("serviceA1");//这个方法应该就是销毁bean的销毁动作的方法，执行了这个方法，bean就销毁了，这个方法只触发指定bean的销毁。
                                                        // 上面postProcessBeforeDestruction方法是销毁前要执行的方法。
        System.out.println("触发所有单例bean的销毁");
        factory.destroySingletons();//这个是销毁所有单例bean的方法，触发所有单例bean的销毁
    
        /**
         * 打印：
         * create class com.mdl.Bean生命周期详解.demo12.ServiceA_230722
         * create class com.mdl.Bean生命周期详解.demo12.ServiceA_230722
         * create class com.mdl.Bean生命周期详解.demo12.ServiceA_230722
         * 销毁serviceA1
         * 准备销毁bean：serviceA1
         * 触发所有单例bean的销毁
         * 准备销毁bean：serviceA3
         * 准备销毁bean：serviceA2
         */
    }
}
