package com.security.study.spring.Bean生命周期详解.demo3;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Arrays;

public class MyDemo {
    public static void main(String[] args) {
        
        // 创建一个bean工厂，这个默认实现了BeanDefinitionRegistry接口，所以也是一个bean注册器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    
        //定义一个bean
        GenericBeanDefinition aaName = new GenericBeanDefinition();
        aaName.setBeanClass(String.class);
        aaName.getConstructorArgumentValues().addIndexedArgumentValue(0, "小明");
    
        //将bean注册到容器中
        beanFactory.registerBeanDefinition("aaName", aaName);
    
        //通过名称获取BeanDefinition
        System.out.println(beanFactory.getBeanDefinition("aaName"));
        //通过名称判断是否注册过BeanDefinition
        System.out.println(beanFactory.containsBeanDefinition("aaName"));
        //获取所有注册的名称
        System.out.println(Arrays.asList(beanFactory.getBeanDefinitionNames()));
        //获取已注册的BeanDefinition的数量
        System.out.println(beanFactory.getBeanDefinitionCount());
        //判断指定的name是否使用过
        System.out.println(beanFactory.isBeanNameInUse("aaName"));
    
        //别名相关方法
        //为name注册2个别名
        beanFactory.registerAlias("aaName", "alias-name-1");
        beanFactory.registerAlias("aaName", "alias-name-2");
        //判断alias-name-1是否已被作为别名使用
        System.out.println(beanFactory.isAlias("alias-name-1"));
        //通过名称获取对应的所有别名
        System.out.println(Arrays.asList(beanFactory.getAliases("aaName")));
        
        
        //最后我们再来获取一下这个bean
        System.out.println(beanFactory.getBean("aaName"));
        
        
        
        
        
    }
}
