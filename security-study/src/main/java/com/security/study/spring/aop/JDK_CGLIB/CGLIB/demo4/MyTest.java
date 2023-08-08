package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo4;

import org.springframework.cglib.proxy.*;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例4：直接放行，不做任何操作（NoOp.INSTANCE）
         *
         * Callback接口下面有个子接口org.springframework.cglib.proxy.NoOp，
         * 将这个作为Callback的时候，被调用的方法会直接放行，像没有任何代理一样，感受一下效果：
         */
    
        /* 扩展：
        Callback接口下面有很多spring已经写好的现成的实现类，可以研究一下都是做什么用的。
         */
        
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service4 proxy = (Service4) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
        
        /*
        运行执行结果：
        我是m1方法
        我是m1返回值
        我是m2方法
        我是m2返回值
         */
        
    
    }
}
