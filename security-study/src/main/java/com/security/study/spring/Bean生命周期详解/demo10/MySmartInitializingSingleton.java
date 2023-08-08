package com.security.study.spring.Bean生命周期详解.demo10;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
    
        System.out.println("所有bean初始化完毕！");
    
    }
}
