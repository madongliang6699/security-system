package com.security.study.spring.aop.aopConception;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author A
 */
public class Test3 {
    
    public static void main(String[] pra) {
        /**
         * 案例3
         * 需求：userName中包含“粉丝”关键字，输出一句：感谢您一路的支持
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
                        // 注意这个地方要返回true
                        return true;
                    }
    
                    /**
                     * 动态匹配验证的方法，比第一个matches方法多了一个参数args，这个参数是调用目标方法传入的参数.
                     * 可以通过这个参数做一些动态的判断,进而过滤特定的方法.
                     * [这个方法的使用逻辑在文章中也有着重介绍,去看一下]
                     */
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        // isRuntime为true的时候，会执行这个方法
                        if (Objects.nonNull(args) && args.length == 1) {
                            String userName = (String) args[0];
                            return userName.contains("粉丝");
                        }
                        return false;
                    }
                };
            }
            
        };
    
        //创建通知，此处需要在方法之前执行操作，所以需要用到MethodBeforeAdvice类型的通知
        MethodBeforeAdvice advice = (method, args, target1) -> System.out.println("感谢您一路的支持!");
        
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
        System.out.println("=================================");
        proxy.work("粉丝小明");
    
        /**
         * 执行结果:
         * 小明,正在工作.......
         * =================================
         * 感谢您一路的支持!
         * 粉丝小明,正在工作.......
         *
         *上面的一些案例中都用到了ProxyFactory这个类，内部将各种对象进行组装，然后创建代理对象，
         * ProxyFactory这块关联的的东西挺多的，下一篇文章将详说这块的东西，是非常重要的内容。
         *
         */
    
    }
    
    
}
