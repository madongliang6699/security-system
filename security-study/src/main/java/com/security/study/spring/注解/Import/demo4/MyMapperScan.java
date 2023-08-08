package com.security.study.spring.注解.Import.demo4;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyMapperScanImportBeanRegister.class)
public @interface MyMapperScan {
    
    
    /**
     * 需要扫描的包路径
     * @return
     */
    String[] basePackages() default {};

}
