package com.security.study.spring.aop.aopSourceCode;

import com.security.study.spring.aop.aopSourceCode.testData.FundsService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class Demo3 {
    
    public static void main(String[] args) {
        /**
         * 案例3
         * 这个案例主要看一下生成的代理对象的一些信息。
         *
         */
    
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new FundsService());
        // /**
        //  *
        // 添加一个方法前置通知，判断用户名不是“路人”的时候，抛出非法访问异常
        //  */
        // proxyFactory.addAdvice(new MethodBeforeAdvice() {
        //     @Override
        //     public void before(Method method, Object[] args, Object target) throws Throwable {
        //         if (!"路人".equals(args[0])) {
        //             throw new RuntimeException("用户名错误");
        //         }
        //     }
        // });
        
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println(method);
            }
        }));
    
        //创建代理对象
        Object proxy = proxyFactory.getProxy();
        System.out.println("代理对象的类型：" + proxy.getClass());
        System.out.println("代理对象的父类：" + proxy.getClass().getSuperclass());
        System.out.println("代理对象实现的接口列表");
        for (Class<?> cf : proxy.getClass().getInterfaces()) {
            System.out.println(cf);
        }
    
        /**
         * 执行结果：
         * 代理对象的类型：class com.mdl.aop.aopSourceCode.testData.FundsService$$EnhancerBySpringCGLIB$$d035c53a
         * 代理对象的父类：class com.mdl.aop.aopSourceCode.testData.FundsService
         * 代理对象实现的接口列表
         * interface org.springframework.aop.SpringProxy
         * interface org.springframework.aop.framework.Advised
         * interface org.springframework.cglib.proxy.Factory
         *
         *
         * 可以看到，代理类是通过cglib创建的，是目标类的子类。
         */
    
    
    }
    
    
}
