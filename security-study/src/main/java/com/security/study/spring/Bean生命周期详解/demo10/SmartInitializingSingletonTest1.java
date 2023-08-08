package com.security.study.spring.Bean生命周期详解.demo10;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;

/**
 * 所有bean初始化完毕，容器会回调{@link SmartInitializingSingleton#afterSingletonsInstantiated()}
 */
@ComponentScan
public class SmartInitializingSingletonTest1 {
    public static void main(String[] args) throws InterruptedException {
    
        // 案例2：通过api的方式让DefaultListableBeanFactory去回调SmartInitializingSingleton
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("service1", BeanDefinitionBuilder.genericBeanDefinition(Service1_230716.class).getBeanDefinition());
        factory.registerBeanDefinition("service2", BeanDefinitionBuilder.genericBeanDefinition(Service2_230716.class).getBeanDefinition());
        factory.registerBeanDefinition("mySmartInitializingSingleton", BeanDefinitionBuilder.genericBeanDefinition(MySmartInitializingSingleton.class).getBeanDefinition());
        System.out.println("准备触发所有单例bean初始化");
        //触发所有bean初始化，并且回调 SmartInitializingSingleton#afterSingletonsInstantiated 方法
        factory.preInstantiateSingletons();
    
        /**
         * 上面通过api的方式注册bean
         * 最后调用factory.preInstantiateSingletons触发所有非lazy单例bean初始化，所有bean装配完毕之后，会回调SmartInitializingSingleton接口。
         */
    }
}
