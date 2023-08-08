package com.security.study.spring.注解.Import.demo3;

import com.security.study.spring.注解.Import.demo1.MyImportTestUser;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanRegister implements ImportBeanDefinitionRegistrar {
    /**
     *
     * 这种方式注入的bean，可以在这里控制这个bean的一些信息，属性之类的。这是和ImportSelector不一样的地方。
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               注册类，其registerBeanDefinition()可以注册bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //构建一个 BeanDefinition , Bean的类型为 MyImportTestUser,这个Bean的属性name的值为小明
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(MyImportTestUser.class)
                .addPropertyValue("name", "小明")
                .getBeanDefinition();
        //把 UserConfig 这个Bean的定义注册到容器中
        registry.registerBeanDefinition("myImportTestUser", beanDefinition);
    }
    
    
}
