package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 为多个接口创建代理
 *
 *代码比较简单，定义了2个接口，然后通过cglib来创建一个代理类，代理类会实现这2个接口，通过setCallback来对2个接口的方法进行增强。
 *
 */
public class CglibTest1 {
    /**
     * 这里定义两个接口
     */
    interface IService1 {
        void m1();
    }
    interface IService2 {
        void m2();
    }
    
    
    
    
    
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
    
        /**
         * 设置代理对象需要实现的接口，这里可以设置多个接口
         */
        enhancer.setInterfaces(new Class[]{IService1.class, IService2.class});
        
        //通过Callback来对被代理方法进行增强
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("方法：" + method.getName());
                return null;
            }
        });
        
        Object proxy = enhancer.create();
        
        //使用
        if (proxy instanceof IService1) {
            ((IService1) proxy).m1();
        }
        if (proxy instanceof IService2) {
            ((IService2) proxy).m2();
        }
        //看一下代理对象的类型
        System.out.println(proxy.getClass());
        //看一下代理类实现的接口
        System.out.println("创建代理类实现的接口如下：");
        for (Class<?> cs : proxy.getClass().getInterfaces()) {
            System.out.println(cs);
        }
    
    
        /**
         * 打印结果：
         * 方法：m1
         * 方法：m2
         * class com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest1$IService1$$EnhancerByCGLIB$$446e8ece
         * 创建代理类实现的接口如下：
         * interface com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest1$IService1
         * interface com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest1$IService2
         * interface org.springframework.cglib.proxy.Factory
         *
         *
         *
         * 上面创建的代理类是代理了两个接口，该代理类就相当于这样的代码，实现了两个接口：
         *
         * public class CglibTest1$IService1$$EnhancerByCGLIB$$446e8ece implements IService1, IService2 {
         *     @Override
         *     public void m1() {
         *         System.out.println("方法：m1");
         *     }
         *     @Override
         *     public void m2() {
         *         System.out.println("方法：m2");
         *     }
         * }
         *
         *
         *
         */
    
    
    
    
    
    }



}
