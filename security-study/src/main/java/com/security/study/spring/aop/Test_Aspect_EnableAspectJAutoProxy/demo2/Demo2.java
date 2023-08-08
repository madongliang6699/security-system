package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo2;

import org.springframework.aop.framework.ProxyFactory;

public class Demo2 {
    public static void main(String[] args) {
        /**
         * 结合外面大文件中讲解4中通知的执行顺序的原理，我们做了这个测试，创建了4个通知。
         *
         * 按照这4中通知最后转换成xxxMethodInterceptor类型的原理，推测执行顺序，前置通知肯定先执行，然后执行目标方法，然后后置通知或者异常通知执行。
         *
         * 根据通知链的执行过程，最终大概变成了下面这样：
         * System.out.println("我是MethodInterceptor start");
         * System.out.println("我是MethodBeforeAdvice");
         * Object retVal = null;
         * try {
         *     retVal = 通过反射调用目标方法获取返回值;
         * } catch (Throwable ex) {
         *     System.out.println("我是ThrowsAdvice");
         *     throw ex;
         * }
         * System.out.println("我是AfterReturningAdvice");
         * System.out.println("我是MethodInterceptor end");
         * return retVal;
         *
         */
        
        
        //创建目标对象
        Service1 target = new Service1();
        //创建代理工厂，通过代理工厂来创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        //依次为目标对象添加4种通知
        // proxyFactory.addAdvice(new MyMethodInterceptor()); //todo MyMethodInterceptor放在MyMethodBeforeAdvice的前面
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());
        // proxyFactory.addAdvice(new MyMethodInterceptor()); //todo MyMethodInterceptor放在MyMethodBeforeAdvice的后面
        proxyFactory.addAdvice(new MyAfterReturningAdvice());
        proxyFactory.addAdvice(new MyMethodInterceptor()); //todo MyMethodInterceptor放在MyAfterReturningAdvice的后面
        proxyFactory.addAdvice(new MyThrowsAdvice());
        //获取到代理对象
        Service1 proxy = (Service1) proxyFactory.getProxy();
        //通过代理对象访问目标方法say
        System.out.println(proxy.say("路人"));
        /**
         * 执行结果:
         * 我是MethodInterceptor start
         * 我是MethodBeforeAdvice
         * 执行 say 方法。。。。。。
         * 我是AfterReturningAdvice
         * 我是MethodInterceptor end
         * 你好：路人
         *
         * 确实是上面推测的逻辑，
         * 但是这里唯一不明白的是：
         * 为什么MyAfterReturningAdvice中的“我是MethodInterceptor start”在前置通知的前面执行。是规定吗？
         * 最后发现是和上面代码里通知类的先后顺序有关,改变成上面的顺序后，执行结果：
         *  我是MethodBeforeAdvice
         * 我是MethodInterceptor start
         * 执行 say 方法。。。。。。
         * 我是MethodInterceptor end
         * 我是AfterReturningAdvice
         * 你好：路人
         *
         * 如果是这样，那spring自动注入的类的先后顺序就不一定了，应该需要特别指定顺序才行，比如用注解指定顺序
         */
    }
}
