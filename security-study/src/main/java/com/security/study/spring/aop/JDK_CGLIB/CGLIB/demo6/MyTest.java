package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo6;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例6：对案例5的优化（CallbackHelper）
         *
         * cglib中有个CallbackHelper类，可以对案例5的代码进行优化，
         * CallbackHelper类相当于对一些代码进行了封装，方便实现案例5的需求，实现如下：
         */
    
    
        Enhancer enhancer = new Enhancer();
        
        
        //创建2个Callback
        //第一个：用来拦截"insert"开头的方法，计时。
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method, Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
        //第二个：下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixdValueCallback = (FixedValue) () -> "路人甲Java";
        
        
        //创建一个CallbackHelper
        CallbackHelper callbackHelper = new CallbackHelper(Service6.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixdValueCallback;
            }
        };
        
        
        enhancer.setSuperclass(Service6.class);
        
        
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        /**
         * 设置CallbackFilter,用来判断某个方法具体走哪个Callback
         * callbackHelper本质是实现了CallbackFilter的抽象类，和案例5是一个东西。
         *进new CallbackHelper(Service6.class, null)构造方法可以看到，只是做了一层使用哪个callback的预先封装。
         */
        enhancer.setCallbackFilter(callbackHelper);
        Service6 proxy = (Service6) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }
    /**
     * 输出效果和案例4一模一样的，上面重点在于CallbackHelper，里面做了一些封装，有兴趣的可以去看一下源码，比较简单。
     */
    
    
}
