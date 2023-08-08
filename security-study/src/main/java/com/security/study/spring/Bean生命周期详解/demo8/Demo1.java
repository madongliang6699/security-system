package com.security.study.spring.Bean生命周期详解.demo8;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;

public class Demo1 {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() { // @0 这个实现方法，在其内部对 user1 这个bean进行属性值信息进行修改。
            @Nullable
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                if ("user1".equals(beanName)) {
                    if (pvs == null) {
                        pvs = new MutablePropertyValues();
                    }
                    if (pvs instanceof MutablePropertyValues) {
                        MutablePropertyValues mpvs = (MutablePropertyValues) pvs;
                        //将姓名设置为：路人
                        mpvs.add("name", "路人");
                        //将年龄属性的值修改为18
                        mpvs.add("age", 18);
                    }
                }
                return null;
            }
        });
        //注意 user1 这个没有给属性设置值
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder.
                genericBeanDefinition(UserModel230709.class).
                getBeanDefinition()); //@1 user1这个bean没有设置属性的值
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder.
                genericBeanDefinition(UserModel230709.class).
                addPropertyValue("name", "刘德华").
                addPropertyValue("age", 50).
                getBeanDefinition());
        
        
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.println(String.format("%s->%s", beanName, factory.getBean(beanName)));
        }
        /**
         * 打印：
         * user1->UserModel{name='路人', age=18}
         * user2->UserModel{name='刘德华', age=50}
         */
    }
}
