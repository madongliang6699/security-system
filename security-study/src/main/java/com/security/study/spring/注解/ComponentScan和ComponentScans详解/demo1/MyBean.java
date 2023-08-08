package com.security.study.spring.注解.ComponentScan和ComponentScans详解.demo1;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 定义自己的注解
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MyBean {
    
    /**
     * 扩展：自定义注解支持定义bean名称
     * 上面的自定义的@MyBean注解，是无法指定bean的名称的，可以对这个注解做一下改造，加个value参数来指定bean的名称，如下：
     *
     * @Documented
     * @Target(ElementType.TYPE)
     * @Retention(RetentionPolicy.RUNTIME)
     * @Component //@1
     * public @interface MyBean {
     *     @AliasFor(annotation = Component.class) //@2
     *     String value() default ""; //@3
     * }
     * 重点在于@1和@2这2个地方的代码，通过上面的参数可以间接给@Component注解中的value设置值。
     *
     * 这块用到了@AliasFor注解，对这块不了解的，可以去看一下：java注解详解及spring对注解的增强
     *
     * 这样MyBean注解就可以给bean指定名字了：
     * @MyBean("service1Bean")
     * public class Service1 {
     * }
     */
    
    
    
    @AliasFor(annotation = Component.class)
    String value() default "";
    
    
    
}
