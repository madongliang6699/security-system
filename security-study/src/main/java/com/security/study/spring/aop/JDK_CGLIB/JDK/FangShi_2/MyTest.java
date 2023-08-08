package com.security.study.spring.aop.JDK_CGLIB.JDK.FangShi_2;

import com.security.study.spring.aop.JDK_CGLIB.JDK.pojo.IService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /**
         * 创建代理对象有更简单的方式：
         * JDK创建动态代理的方式二：
         *
         * 步骤：
         * 1.使用InvocationHandler接口创建代理类的处理器
         * 2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
         * 3.使用代理对象
         */

        // 1. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());

                return null;
            }
        };
        // 2. 创建代理实例
        IService iService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        // 4. 调用代理的方法
        iService.m1();
        iService.m2();
        iService.m3();

    }



}
