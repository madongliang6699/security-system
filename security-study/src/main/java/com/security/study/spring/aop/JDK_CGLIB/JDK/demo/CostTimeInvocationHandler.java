package com.security.study.spring.aop.JDK_CGLIB.JDK.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 拥有耗时统计功能的动态代理处理器
 */
public class CostTimeInvocationHandler implements InvocationHandler {

    //被代理的目标对象
    private Object target;

    public CostTimeInvocationHandler(Object target){
        this.target = target;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long starTime = System.nanoTime();
        Object result = method.invoke(target, args);//调用被代理对象的原始方法
        long endTime = System.nanoTime();
        //通过这样的方式，在被代理对象的方法的调用前后我们就可以做功能增强。
        System.out.println(this.target.getClass() + "."+method.getName()+"()方法耗时(纳秒):" + (endTime - starTime));
        return result;
    }


    /**
     * 这里写一个创建代理对象的的方法，其实这个方法写在哪里都行，做成一个工具类也行，但是直接写在这里，在使用的时候比较方便一些。
     * 这样，下面Proxy.newProxyInstance()方法中的InvocationHandler参数就直接new出一个当前处理器对象就行了（new CostTimeInvocationHandler(target)）。
     * 如果写在其他工具类里面还要作为一个参数传入。
     * @param target
     * @return
     * @param <T>
     */
    public static <T> T createProxyInstance(Object target){

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                            target.getClass().getInterfaces(),
                                            new CostTimeInvocationHandler(target));
    }


}
