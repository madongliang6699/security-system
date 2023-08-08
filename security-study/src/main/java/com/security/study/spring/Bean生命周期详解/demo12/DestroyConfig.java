package com.security.study.spring.Bean生命周期详解.demo12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DestroyConfig {
    
    @Bean(destroyMethod = "customDestroyMethod") //@1 通过destroyMethod属性将customDestroyMethod指定为自定义销毁方法
    public ServiceC_230722 serviceC() {
        return new ServiceC_230722();
    }



}
