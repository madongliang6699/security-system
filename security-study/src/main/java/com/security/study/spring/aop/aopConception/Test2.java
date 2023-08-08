package com.security.study.spring.aop.aopConception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author A
 */
public class Test2 {
    
    public static void main(String[] args) {
        /**
         * 案例2
         * 需求：统计一下work方法的耗时，将耗时输出
         */
        
        
        //定义目标对象
        UserService userService = new UserService();
        
        
        //创建pointcut，用来拦截UserService中的work方法
        Pointcut pointcut = new Pointcut() {
            
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是UserService类型的
                return UserService.class::isAssignableFrom;
            }
            
            
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    /**
                     * 执行静态检查给定方法是否匹配
                     * @param method 目标方法
                     * @param targetClass 目标对象类型
                     */
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        //判断方法名称是否是work
                        return "work".equals(method.getName());
                    }
    
                    /**
                     * 是否是动态匹配，即是否每次执行目标方法的时候都去验证一下
                     */
                    @Override
                    public boolean isRuntime() {
                        return false;
                    }
    
                    /**
                     * 动态匹配验证的方法，比第一个matches方法多了一个参数args，这个参数是调用目标方法传入的参数.
                     * [这个方法的使用逻辑在文章中也有着重介绍,去看一下]
                     */
                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
            
        };
    
        //创建通知，需要拦截方法的执行，所以需要用到MethodInterceptor类型的通知
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("准备调用:" + invocation.getMethod());
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + "，调用结束！");
                System.out.println("耗时(纳秒):" + (endTime - starTime));
                return result;
            }
        };
        
        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, methodInterceptor);
        
        //通过spring提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(userService);
        //调用addAdvisor方法，为目标添加增强的功能，即添加Advisor，可以为目标添加很多个Advisor
        proxyFactory.addAdvisor(advisor);
        //通过工厂提供的方法来生成代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();
        proxy.work("小明");
    
    
        /**
         * 执行结果:
         * 准备调用:public void com.mdl.aop.aopConception.UserService.work(java.lang.String)
         * 小明,正在工作.......
         * public void com.mdl.aop.aopConception.UserService.work(java.lang.String)，调用结束！
         * 耗时(纳秒):12235800
         *
         *
         *
         */
    
    }
    
    
}
