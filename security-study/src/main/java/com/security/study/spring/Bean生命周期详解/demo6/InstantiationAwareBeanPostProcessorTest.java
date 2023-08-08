package com.security.study.spring.Bean生命周期详解.demo6;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * bean初始化前阶段，会调用：{@link org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessBeforeInitialization(Object, String)}
 */
public class InstantiationAwareBeanPostProcessorTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    
        // 创建一个InstantiationAwareBeanPostProcessor，丢到了容器中的BeanPostProcessor列表中
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                System.out.println("调用postProcessBeforeInstantiation()");
                //发现类型是Car230709类型并且bean名字是car1的时候，硬编码创建一个Car230709对象返回
                if (beanClass == Car230709.class && beanName.equals("car1")) {
                    System.out.println("符合条件...");
                    Car230709 car = new Car230709();
                    car.setName("保时捷");
                    return car;
                }
                return null;
            }
        });
        
        // 定义一个car bean,车名为：奥迪
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.
                genericBeanDefinition(Car230709.class).
                addPropertyValue("name", "奥迪").  //@2 创建了一个car bean，name为奥迪
                        getBeanDefinition();
        factory.registerBeanDefinition("car", carBeanDefinition);
        
        // 定义一个car1 bean,车名为：比亚迪
        AbstractBeanDefinition carBeanDefinition1 = BeanDefinitionBuilder.
                genericBeanDefinition(Car230709.class).
                addPropertyValue("name", "比亚迪").
                        getBeanDefinition();
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        
        
        
        //从容器中获取car这个bean的实例，输出
        System.out.println(factory.getBean("car"));
        System.out.println(factory.getBean("car1"));
    
        /**
         * 打印：
         * 调用postProcessBeforeInstantiation()
         * Car{name='奥迪'}
         * 调用postProcessBeforeInstantiation()
         * 符合条件...
         * Car{name='保时捷'}
         *
         * 比亚迪 换成了 保时捷。
         * 定义和输出不一致的原因是因为我们在InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation方法中手动创建了一个实例直接返回了，
         * 而不是依靠spring内部去创建这个实例。
         *
         * 总结：从打印的情况来看，每个bean在实例化之前都调用了这个postProcessBeforeInstantiation方法，
         * beanClass参数是接下来要实例化的bean类型，beanName是bean的名称，可以根据这两个参数来定位是不是自己要操作的bean，
         * 然后自己去根据情况，在该bean实例化之前就替换成自己想要的bean。
         *
         * 小结
         * 实际上，在实例化前阶段对bean的创建进行干预的情况，用的非常少，所以大部分bean的创建还会继续走下面的阶段。
         */
    }
}
