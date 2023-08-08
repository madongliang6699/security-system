package com.security.study.spring.Bean生命周期详解.demo2;

import com.security.study.spring.Bean生命周期详解.demo1.Car230611;
import com.security.study.spring.Bean生命周期详解.demo1.User230611;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class MyDemo {
    public static void main(String[] args) {
    
        /**
         * xml解析
         */
    
        /*
        
        
        //定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //定义一个xml的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注册器）对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        //指定bean xml配置文件的位置
        String location = "classpath:/com/javacode2018/lesson002/demo2/beans.xml";
        //通过XmlBeanDefinitionReader加载bean xml文件，然后将解析产生的BeanDefinition注册到容器容器中
        int countBean = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(String.format("共注册了 %s 个bean", countBean));
        //打印出注册的bean的配置信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    
        
    //注意一点：创建XmlBeanDefinitionReader的时候需要传递一个bean注册器(BeanDefinitionRegistry)，解析过程中生成的BeanDefinition会丢到bean注册器中。
    
    // 上面的输出认真看一下，这几个BeanDefinition都是GenericBeanDefinition（BeanDefinition接口的其中一个实现类，阶段1里有说）这种类型的，
        // 也就是说xml中定义的bean被解析之后都是通过GenericBeanDefinition这种类型表示的。
    
    
    
    */
    
        
    
        
        /**
         * 注解解析
         */
        //定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory2 = new DefaultListableBeanFactory();
        //定义一个注解方式的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注册器）对象
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory2);
        //通过PropertiesBeanDefinitionReader加载bean properties文件，然后将解析产生的BeanDefinition注册到容器容器中
        annotatedBeanDefinitionReader.register(Car230611.class, User230611.class);
    
        factory2.getBeansOfType(BeanPostProcessor.class).values().forEach(factory2::addBeanPostProcessor); // @1 这一行的作用以后会讲，有了这一行，才能注入Car230611的bean到User230611中，要不然即便有@Autowired也不行
        
        //打印出注册的bean的配置信息
        for (String beanName : new String[]{"user230611", "car230611"}) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory2.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory2.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    
    
    }
}
