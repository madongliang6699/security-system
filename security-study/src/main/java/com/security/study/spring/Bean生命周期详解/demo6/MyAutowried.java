package com.security.study.spring.Bean生命周期详解.demo6;

import java.lang.annotation.*;

/**
 * 下面这个注解可以标注在构造器上面，使用这个标注之后，创建bean的时候将使用这个构造器。
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowried {

}
