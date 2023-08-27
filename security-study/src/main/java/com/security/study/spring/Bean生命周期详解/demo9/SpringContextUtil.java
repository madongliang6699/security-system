package com.security.study.spring.Bean生命周期详解.demo9;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;

/**
 * 利用这里接口，可以做一个spring的工具类，工具类里面可以通过这些接口拿到一些内置对象。
 * 再做一些静态方法的封装和优化，就是一个不错的工具类了。
 *
 * 这种方式做的工具类在工作中见到过别人用。
 */
public class SpringContextUtil implements EnvironmentAware, EmbeddedValueResolverAware,
        ResourceLoaderAware, ApplicationEventPublisherAware,
        MessageSourceAware, ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    private static Environment environment;
    // @Autowired
    private static ApplicationEventPublisher eventPublisher;
    /*
    其实这里的对象直接用@Autowired注入也可以啊，在需要使用的地方注入一下就行了， 没必要使用下面的setXXX方法的形式吧。
     */
    
    public SpringContextUtil() {
    }
    
    
    @PostConstruct
    public void postConstruct1() { //@1
        System.out.println("postConstruct1()");
    }
    
    @PostConstruct
    public void postConstruct2() { //@2
        System.out.println("postConstruct2()");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext:" + applicationContext);
        this.applicationContext = applicationContext;
        // 这里也可以拿到environment对象，和下面应该是一样的效果
        this.environment = applicationContext.getEnvironment();
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("setApplicationEventPublisher:" + applicationEventPublisher);
        eventPublisher = applicationEventPublisher;
    }
    
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("setEmbeddedValueResolver:" + resolver);
    }
    
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("setEnvironment:" + environment.getClass());
        //这里可以拿到environment对象，和上面应该是一样的效果
        this.environment = environment;
    }
    
    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("setMessageSource:" + messageSource);
    }
    
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("setResourceLoader:" + resourceLoader);
    }
    
    
    
    
    //todo  下面可以为这个工具类添加一下方法使用
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    
    public static <T> T getBean(Class<T> clazz) {
        return clazz == null ? null : getApplicationContext().getBean(clazz);
    }
    
    
}
