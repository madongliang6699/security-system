package com.security.study.spring.aop.aopConception;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author A
 */
public class Test1 {
    
    public static void main(String[] args) {
        /**
         * 案例1
         * 需求：在work方法执行之前，打印一句：你好：userName
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
        
        //创建通知，此处需要在方法之前执行操作，所以需要用到MethodBeforeAdvice类型的通知
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object o) throws Throwable {
                System.out.println("你好: " + args[0]);
                //method.invoke(o, args);//这里不需要调用,否则重复调用.因为用这个MethodBeforeAdvice, spring本身就会帮我们调用.
            }
        };
        
        //创建顾问Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, methodBeforeAdvice);
        
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
         * 你好: 小明
         * 小明,正在工作.......
         *
         *
         *
         *
         * 上面是采用硬编码的方式来感受一下aop的用法，大家看了上面代码之后，估计会有疑问：我晕，这么复杂？？？
         * 如果大家有使用过spring中的aop经验，可能只需要几行代码就实现了上面的功能，的确，spring中把整个功能简化了很多，
         * 不过我们得去了解他的内部是如何实现的，然后才能走的更远。
         */
    
    }
    
    
}
