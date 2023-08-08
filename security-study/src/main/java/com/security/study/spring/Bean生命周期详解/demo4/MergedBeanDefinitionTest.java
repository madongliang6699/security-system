package com.security.study.spring.Bean生命周期详解.demo4;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanDefinition 合并
 */
public class MergedBeanDefinitionTest  {
    public static void main(String[] args) {
    
        /**
         * 下面将解析xml，进行bean注册，然后遍历输出bean的名称，解析过程中注册的原始的BeanDefinition，合并之后的BeanDefinition，
         * 以及合并前后BeanDefinition中的属性信息
         */
    
        //创建bean容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //创建一个bean xml解析器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);
        //解析bean xml，将解析过程中产生的BeanDefinition注册到DefaultListableBeanFactory中
        beanDefinitionReader.loadBeanDefinitions("com/mdl/spring/Bean生命周期详解/demo4/aa.xml");
        //遍历容器中注册的所有bean信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            //通过bean名称获取原始的注册的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取合并之后的BeanDefinition信息
            BeanDefinition mergedBeanDefinition = factory.getMergedBeanDefinition(beanName);
            System.out.println(beanName);
            System.out.println("解析xml过程中注册的beanDefinition： " + beanDefinition);
            System.out.println("beanDefinition中的属性信息: " + beanDefinition.getPropertyValues());
            System.out.println("合并之后得到的mergedBeanDefinition：" + mergedBeanDefinition);
            System.out.println("mergedBeanDefinition中的属性信息：" + mergedBeanDefinition.getPropertyValues());
            System.out.println("---------------------------");
        }
    
        /**
         * 从输出的结果中可以看到，合并之前，BeanDefinition是不完整的，比lesson2和lesson3中的class是null，属性信息也不完整，但是合并之后这些信息都完整了。
         *
         * 合并之前是GenericBeanDefinition类型的，合并之后得到的是RootBeanDefinition类型的。
         *
         * 获取lesson3合并的BeanDefinition时，内部会递归进行合并，先将lesson1和lesson2合并，然后将lesson2再和lesson3合并，最后得到合并之后的BeanDefinition。
         *
         * 后面的阶段将使用合并产生的RootBeanDefinition。
         */
    
    }
}
