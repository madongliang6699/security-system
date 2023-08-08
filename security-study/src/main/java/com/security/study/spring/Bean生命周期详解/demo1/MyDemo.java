package com.security.study.spring.Bean生命周期详解.demo1;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class MyDemo {
    
    public static void main(String[] args) {
    
        /**
         * 案例1
         */
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car230611.class.getName());
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);
    
        //等效于: <bean class="com.javacode2018.lesson002.demo1.Car" />
    
    
        /**
         * 案例2：给bean设置属性值
         */
        beanDefinitionBuilder.addPropertyValue("name", "比亚迪秦");
        BeanDefinition beanDefinition2 = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition2);
    
        //创建spring容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //调用registerBeanDefinition向容器中注册bean
        beanFactory.registerBeanDefinition("car", beanDefinition2);
        Car230611 car = beanFactory.getBean("car", Car230611.class);
        System.out.println(car);
    
        /**
         * 案例3：组装一个有依赖关系的bean
         *
         * 等效于：
         * <bean id="car" class="com.javacode2018.lesson002.demo1.Car">
         *     <property name="name" value="奥迪"/>
         * </bean>
         * <bean id="user" class="com.javacode2018.lesson002.demo1.User">
         *     <property name="name" value="路人甲Java"/>
         *     <property name="car" ref="car"/>
         * </bean>
         */
        //先创建car这个BeanDefinition
        BeanDefinition carBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Car230611.class.getName())
                .addPropertyValue("name", "奥迪")
                .getBeanDefinition();
        
        //创建User这个BeanDefinition
        BeanDefinition userBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User230611.class.getName())
                .addPropertyValue("name", "路人甲Java")
                .addPropertyReference("car", "car") //@1 注入依赖的bean，需要使用addPropertyReference方法，2个参数，第一个为属性的名称，第二个为需要注入的bean的名称
                .getBeanDefinition();
        
        //创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //调用registerBeanDefinition向容器中注册bean
        factory.registerBeanDefinition("car", carBeanDefinition); //这里bean名称是car
        factory.registerBeanDefinition("user", userBeanDefinition);
        System.out.println(factory.getBean("car"));
        System.out.println(factory.getBean("user")); //User{name='路人甲Java', car=Car{name='奥迪'}}
    
    
        /**
         * 案例4：来2个有父子关系的bean
         *
         *
         * <bean id="car1" class="com.javacode2018.lesson002.demo1.Car">
         *     <property name="name" value="保时捷"/>
         * </bean>
         * <bean id="car2" parent="car1" />
         */
        //先创建car这个BeanDefinition
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder.
                genericBeanDefinition(Car230611.class).
                addPropertyValue("name", "保时捷").
                getBeanDefinition();
        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder.
                genericBeanDefinition(). //内部生成一个GenericBeanDefinition对象
                        setParentName("car1"). //@1：设置父bean的名称为car1
                        getBeanDefinition();
        //创建spring容器
        DefaultListableBeanFactory factory2 = new DefaultListableBeanFactory();
        //调用registerBeanDefinition向容器中注册bean
        //注册car1->carBeanDefinition1
        factory2.registerBeanDefinition("car1", carBeanDefinition1);
        //注册car2->carBeanDefinition2
        factory2.registerBeanDefinition("car2", carBeanDefinition2);
        //从容器中获取car1
        System.out.println(String.format("car1->%s", factory2.getBean("car1")));
        //从容器中获取car2
        System.out.println(String.format("car2->%s", factory2.getBean("car2")));
    
    
        /**
         * 案例5：通过api设置（Map、Set、List）属性
         *
         * 下面我们来演示注入List、Map、Set，内部元素为普通类型及其他bean元素。
         */
    //    这里不写了，看原文
    
    
    }
    
}
