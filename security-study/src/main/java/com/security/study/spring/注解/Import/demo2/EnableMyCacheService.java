package com.security.study.spring.注解.Import.demo2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyCacheImportSelector.class)
public @interface EnableMyCacheService {

    //这个参数就像是一个开关或者配置，控制使用Redis缓存或者本地缓存，具体的控制逻辑在MyCacheImportController里面
    CacheType type() default CacheType.REDIS;

}
