package com.security.study.spring.aop.JDK_CGLIB.CGLIB.useDemo1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 计时器功能的 cglib动态代理生成器/拦截器。
 * 实现通用的统计任意类方法耗时代理类
 */
public class CostTimeProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        Object result = methodProxy.invokeSuper(o, objects);
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
        return result;
    }
    
    
    /**
     * 创建cglib方式的代理对象
     * @param tagetObject
     * @return
     */
    public static <T> T createProxy(T tagetObject) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tagetObject.getClass());
        enhancer.setCallback(new CostTimeProxy());
        return (T) enhancer.create();
    }
}
