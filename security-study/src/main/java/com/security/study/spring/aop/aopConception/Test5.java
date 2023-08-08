package com.security.study.spring.aop.aopConception;

import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author A
 */
public class Test5 {
    
    public static void main(String[] pra) {
        /**
         * 案例5
         * 需求：在work方法中抛出一个异常，然后通过aop中的ThrowsAdvice类型的通知来拦截这个异常信息，然后将异常错误信息打印出来
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
        
        //创建通知，此处需要在方法抛出异常之后执行操作，所以需要用到ThrowsAdvice类型的通知
        ThrowsAdvice throwsAdvice = new ThrowsAdvice(){
            public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
                System.out.println("错误信息:");
                //throwable.printStackTrace();
                System.out.println(ex.getMessage());
            }
        };
        
        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TestThrowsAdvice());
        //todo 注意下面这样直接使用throwsAdvice这个匿名内部类的通知是不行的，因为这样的匿名内部类不是public的类，cglib底层没法通过反射创建代理，因为没有操作类的权限。
        //DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, throwsAdvice);
        
        
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
         * 错误信息:
         * / by zero
         * Exception in thread "main" java.lang.ArithmeticException: / by zero
         *
         *
         */
        
    }
    
    
    public static class TestThrowsAdvice implements ThrowsAdvice {
        public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
            System.out.println("错误信息:");
            //throwable.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    
    
}
