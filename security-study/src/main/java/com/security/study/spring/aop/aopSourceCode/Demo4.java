package com.security.study.spring.aop.aopSourceCode;


import com.security.study.spring.aop.aopSourceCode.testData.IService1;
import com.security.study.spring.aop.aopSourceCode.testData.Service1;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class Demo4 {
    
    public static void main(String[] args) {
        /**
         * 案例4 代理接口
         * 有接口的情况默认会通过jdk动态代理的方式生成代理，下面来看一下。
         *
         */
    
    
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Service1());
        proxyFactory.setInterfaces(IService1.class);
        
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println(method);
            }
        });
    
    
        //强制使用cglib代理
        proxyFactory.setProxyTargetClass(true);
    
        IService1 proxy = (IService1) proxyFactory.getProxy();
        System.out.println("代理对象的类型：" + proxy.getClass());
        System.out.println("代理对象的父类：" + proxy.getClass().getSuperclass());
        System.out.println("代理对象实现的接口列表");
        for (Class<?> cf : proxy.getClass().getInterfaces()) {
            System.out.println(cf);
        }
        //调用代理的方法
        System.out.println("\n调用代理的方法");
        proxy.say("spring aop");
    
        /**
         *执行结果：
         * 代理对象的类型：class com.sun.proxy.$Proxy0
         * 代理对象的父类：class java.lang.reflect.Proxy
         * 代理对象实现的接口列表
         * interface com.mdl.aop.aopSourceCode.testData.IService
         * interface org.springframework.aop.SpringProxy
         * interface org.springframework.aop.framework.Advised
         * interface org.springframework.core.DecoratingProxy
         *
         * 调用代理的方法
         * public abstract void com.mdl.aop.aopSourceCode.testData.IService.say(java.lang.String)
         * hello: spring aop
         *
         *
         *
         *
         * 总结：
         * 从第一行输出中可以看出是采用jdk动态代理方式创建的代理
         * 第二行验证了，所有通过jdk动态代理方式创建的代理对象都是Proxy的子类
         * 输出的接口列表中可以看出，默认帮我们实现了3个接口[SpringAop,Advised,DecoratingProxy]
         *
         * 但是：
         * 加了这一句：proxyFactory.setProxyTargetClass(true); 之后，就强制使用cglib代理了。
         *
         */
    
    
    }
    
    
}
