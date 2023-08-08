package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例2：拦截所有方法（MethodInterceptor）
         * 案例2创建代理的方式和案例1是一样的，不同的是被代理类本身是通过自身内部方法的相互调用，来起到所有方法都被调用的，
         * 每个方法被调用的时候都能被cglib拦截代理。
         *
         * 当然毕竟m2方法作为了m1方法的一部分，那么m1方法的代理方法拦截器的后置增强逻辑，就会在m2执行完之后再执行，
         * 什么意思呢：看下面的执行结果和案例1的执行结果对比就知道了。
         */
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor(){
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法"+method+"之前。。。。");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("调用方法"+method+"之后。。。。");
                return result;
            }
        });
        Service2 service2Proxy  = (Service2) enhancer.create();
        service2Proxy .m1();
        
        /*
        执行结果：
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo2.Service2.m1()之前。。。。
        我是m1方法
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo2.Service2.m2()之前。。。。
        我是m2方法
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo2.Service2.m2()之后。。。。
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo2.Service2.m1()之后。。。。
         */
        
    
    }
}
