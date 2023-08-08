package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo5;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例5：不同的方法使用不同的拦截器（CallbackFilter）
         *
         * 需求，给Service5这个类创建一个代理需要实现下面的功能：
         * 以insert开头的方法需要统计方法耗时
         * 以get开头的的方法直接返回固定字符串: 欢迎和【路人甲java】一起学spring！
         */
        
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service5.class);
    
        //创建2个Callback
        Callback[] callbacks = {
                //这个用来拦截所有insert开头的方法
            new MethodInterceptor(){
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    long start = System.currentTimeMillis();
                    Object result = methodProxy.invokeSuper(o, objects);
                    long end = System.currentTimeMillis();
                    System.out.println("我是" + method.getName() + "方法,耗时：" + (end - start) + "毫秒");
                    return result;
                };
            },
                //下面这个用来拦截所有get开头的方法，返回固定值的
            new FixedValue(){
                @Override
                public Object loadObject() throws Exception {
                    return "欢迎和【路人甲java】一起学spring！";
                }
            }
        };
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         * 返回的是callbacks数组的 “下标”
         */
        enhancer.setCallbackFilter(new CallbackFilter(){
            /**
             * 方法名称以insert开头，
             * 返回callbacks中的第1个Callback对象来处理当前方法，
             * 否则使用第二个Callback处理被调用的方法
             */
            @Override
            public int accept(Method method) {
                return method.getName().startsWith("insert") ? 0 : 1;
            }
        });
        
        Service5 proxy = (Service5) enhancer.create();
        proxy.insert1();
        proxy.insert2();
        System.out.println(proxy.get1());
        System.out.println(proxy.get2());
        
        /*
        运行执行结果：
        我是insert1
        我是insert1方法,耗时：15毫秒
        我是insert2
        我是insert2方法,耗时：0毫秒
        欢迎和【路人甲java】一起学spring！
        欢迎和【路人甲java】一起学spring！
         */
        /**
         *   代码说明：
         *
         * 由于需求中要对不同的方法做不同的处理，所以需要有2个Callback对象，
         * 当调用代理对象的方法的时候，具体会走哪个Callback呢，此时会通过CallbackFilter中的accept来判断，
         * 这个方法返回callbacks数组的索引。
         *
         * 这个案例还有简单的写法，见案例6
         */
        
    
    }
}
