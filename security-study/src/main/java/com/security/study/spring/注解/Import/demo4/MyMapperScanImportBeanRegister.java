package com.security.study.spring.注解.Import.demo4;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;

public class MyMapperScanImportBeanRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    
    
    ResourceLoader resourceLoader;
    
    /**
     * 搜索指定包下所有添加了MyMapper注解的类，并且把这些类添加到ioc容器里面去
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               注册类，其registerBeanDefinition()可以注册bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //1. 从BeanIocScan注解获取到我们要搜索的包路径
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyMapperScan.class.getName());
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationAttributes);
        
        if (annoAttrs == null || annoAttrs.isEmpty()) {
            return;
        }
        String[] basePackages = (String[]) annoAttrs.get("basePackages");
        // 2. 找到指定包路径下所有添加了MapperBean注解的类，并且把这些类添加到IOC容器里面去
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(registry, false);
        classPathBeanDefinitionScanner.setResourceLoader(resourceLoader);
        //路径包含MapperBean的注解的bean
        classPathBeanDefinitionScanner.addIncludeFilter(new AnnotationTypeFilter(MyMapper.class));
        //扫描包下路径
        classPathBeanDefinitionScanner.scan(basePackages);
    }
    
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
