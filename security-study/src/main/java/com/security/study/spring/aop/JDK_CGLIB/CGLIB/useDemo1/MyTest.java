package com.security.study.spring.aop.JDK_CGLIB.CGLIB.useDemo1;


public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 实战案例：实现通用的统计任意类方法耗时代理类
         *
         */
    
        
        Service7 proxy = CostTimeProxy.createProxy(new Service7());
        proxy.insert1();
        proxy.insert2();
        proxy.get1();
        proxy.get2();
    
        Service8 proxy8 = CostTimeProxy.createProxy(new Service8());
        proxy8.insert1();
        proxy8.insert2();
        String proxy81 = proxy8.get1();
        System.out.println(proxy81);
        proxy8.get2();
        
    
    }
}
