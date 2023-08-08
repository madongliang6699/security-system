package com.security.study.spring.aop.JDK_CGLIB.JDK.FangShi_1;

import com.security.study.spring.aop.JDK_CGLIB.JDK.pojo.IService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /**
         * JDK创建动态代理的方式一：
         *
         * 步骤：
         * 1.调用Proxy.getProxyClass方法获取代理类的Class对象
         * 2.使用InvocationHandler接口创建代理类的处理器
         * 3.通过代理类和InvocationHandler创建代理对象
         * 4.上面已经创建好代理对象了，接着我们就可以使用代理对象了
         */

        // 1. 获取接口对应的代理类
        Class<IService> proxyClass = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        // 2. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());

                return null;
            }
        };
        // 3. 创建代理实例
        IService iService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4. 调用代理的方法
        iService.m1();
        iService.m2();
        iService.m3();

    }



}
