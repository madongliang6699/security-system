package com.security.study.spring.Bean生命周期详解.demo8;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * {@link InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(Object, String)}
 * 返回false，可以阻止bean属性的赋值
 */
public class InstantiationAwareBeanPostProcessoryTest1 {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder.
                genericBeanDefinition(UserModel230709.class).
                addPropertyValue("name", "路人甲Java").
                addPropertyValue("age", 30).
                getBeanDefinition());
        
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder.
                genericBeanDefinition(UserModel230709.class).
                addPropertyValue("name", "刘德华").
                addPropertyValue("age", 50).
                getBeanDefinition());
        
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.println(String.format("%s->%s", beanName, factory.getBean(beanName)));
        }
        
        /*
         * user1->UserModel{name='路人甲Java', age=30}
         * user2->UserModel{name='刘德华', age=50}
         *
         * 此时UserModel中2个属性都是有值的。
         *
         *
         *
         *
         *下面来阻止user1的赋值，对代码进行改造，加入下面代码：
         * */
    
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                if ("user1".equals(beanName)) {
                    return false;
                } else {
                    return true;
                }
            }
        });
    
        /**
         * 输出：
         *
         * user1->UserModel{name='null', age=null}
         * user2->UserModel{name='刘德华', age=50}
         * user1的属性赋值被跳过了。
         */
    }
}
