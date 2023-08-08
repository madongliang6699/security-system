package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 为 类和接口 同时创建代理
 *
 *下面定义了2个接口：IService1和IService2，2个接口有个实现类：Service，然后通过cglib创建了个代理类，实现了这2个接口，并且将Service类作为代理类的父类。
 *
 */
public class CglibTest2 {
    /**
     * 这里定义两个接口
     */
    interface IService1 {
        void m1();
    }
    interface IService2 {
        void m2();
    }
    /**
     * 实现类
     */
    public static class Service implements IService1, IService2 {
        @Override
        public void m1() {
            System.out.println("m1");
        }
        @Override
        public void m2() {
            System.out.println("m2");
        }
    }
    
    
    
    
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类
        enhancer.setSuperclass(Service.class);
        //设置代理对象需要实现的接口
        enhancer.setInterfaces(new Class[]{IService1.class, IService2.class});
        //通过Callback来对被代理方法进行增强
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                long startime = System.nanoTime();
                Object result = methodProxy.invokeSuper(o, objects); //调用父类中的方法
                System.out.println(method + "，耗时(纳秒):" + (System.nanoTime() - startime));
                return result;
            }
        });
        //创建代理对象
        Object proxy = enhancer.create();
        //判断代理对象是否是Service类型的
        System.out.println("proxy instanceof Service：" + (proxy instanceof Service));
        if (proxy instanceof Service) {
            Service service = (Service) proxy;
            service.m1();
            service.m2();
        }
        //看一下代理对象的类型
        System.out.println(proxy.getClass());
        //输出代理对象的父类
        System.out.println("代理类的父类：" + proxy.getClass().getSuperclass());
        //看一下代理类实现的接口
        System.out.println("创建代理类实现的接口如下：");
        for (Class<?> cs : proxy.getClass().getInterfaces()) {
            System.out.println(cs);
        }
    
    
        /**
         * 打印结果：
         *proxy instanceof Service：true
         * m1
         * public void com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$Service.m1()，耗时(纳秒):9072400
         * m2
         * public void com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$Service.m2()，耗时(纳秒):76200
         * class com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$Service$$EnhancerByCGLIB$$54298e91
         * 代理类的父类：class com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$Service
         * 创建代理类实现的接口如下：
         * interface com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$IService1
         * interface com.mdl.aop.JDK_CGLIB.CGLIB.otherDemo.CglibTest2$IService2
         * interface org.springframework.cglib.proxy.Factory
         *
         *
         * 上面创建的代理类相当于下面代码：
         * public class CglibTest2$Service$$EnhancerByCGLIB$$54298e91 extends Service implements IService1, IService2 {
         *     @Override
         *     public void m1() {
         *         long starttime = System.nanoTime();
         *         super.m1();
         *         System.out.println("方法m1，耗时(纳秒):" + (System.nanoTime() - starttime));
         *     }
         *     @Override
         *     public void m2() {
         *         long starttime = System.nanoTime();
         *         super.m1();
         *         System.out.println("方法m1，耗时(纳秒):" + (System.nanoTime() - starttime));
         *     }
         * }
         *
         *
         */
    
    
    
    
    
    }



}
