package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例1：拦截所有方法（MethodInterceptor）
         */
    
    
        // 下面我们为Service1这个类创建一个代理，代理中实现打印每个方法的调用日志。

        //使用Enhancer（增强器）来给某个类创建代理类，
        // 步骤：
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类，因为代理类就是被代理类的子类，所以叫设置父类。
        enhancer.setSuperclass(Service1.class);
        /* 3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor（方法拦截器），也是一个接口，实现了Callback接口，
        这个Callback接口是spring中的cglib中的接口。
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback(new MethodInterceptor(){
            /**
             * 代理对象方法拦截器
             * @param o 代理对象
             * @param method 被代理的类的方法，即Service1中的方法
             * @param objects 调用方法传递的参数
             * @param methodProxy 方法代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法"+method+"之前。。。。");
                //可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("调用方法"+method+"之后。。。。");
                return result;
            }
        });
        
        //4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 service1Proxy  = (Service1) enhancer.create();
        //5.调用代理对象的方法
        service1Proxy .m1();
        service1Proxy .m2();
        
        /*
        执行结果：
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo1.Service1.m1()之前。。。。
        我是m1方法
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo1.Service1.m1()之后。。。。
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo1.Service1.m2()之前。。。。
        我是m2方法
        调用方法public void com.mdl.aop.JDK_CGLIB.CGLIB.demo1.Service1.m2()之后。。。。
         */
        
    
    }
}
