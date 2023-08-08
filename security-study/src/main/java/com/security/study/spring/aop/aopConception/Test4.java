package com.security.study.spring.aop.aopConception;

import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author A
 */
public class Test4 {
    
    public static void main(String[] pra) {
        /**
         * 案例4
         * 需求：work方法执行之后，打印一句：再见：userName
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
                     * 可以通过这个参数做一些动态的判断,进而过滤特定的方法.
                     * [这个方法的使用逻辑在文章中也有着重介绍,去看一下]
                     */
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
            
        };
        
        //创建通知，此处需要在方法之后执行操作，所以需要用到MethodBeforeAdvice类型的通知
        AfterReturningAdvice advice = new AfterReturningAdvice() {
            /**
             * 不过需要注意一点：目标方法正常执行后，才会回调这个接口，当目标方法有异常，那么这通知会被跳过。
             *
             * @param returnValue the value returned by the method, if any
             * @param method method being invoked
             * @param args arguments to the method
             * @param target target of the method invocation. May be {@code null}.
             * @throws Throwable
             */
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                //System.out.println(returnValue);//打印目标方法返回值
                System.out.println("再见:" + args[0]);
            }
        };
        
        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        
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
         * 小明,正在工作.......
         * 再见:小明
         *
         *
         */
        
    }
    
    
}
