package com.security.study.spring.aop.aopSourceCode;

import com.security.study.spring.aop.aopSourceCode.testData.FundsService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class Demo1 {
    
    public static void main(String[] args) {
        /**
         * 案例1：前置通知拦截非法访问:
         *资金操作的所有方法都需要验证用户名，当用户名不是“路人”的时候，直接抛出非法访问异常。
         */
    
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        /**
         *
        添加一个方法前置通知，判断用户名不是“路人”的时候，抛出非法访问异常
         */
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if (!"路人".equals(args[0])) {
                    throw new RuntimeException("用户名错误");
                }
            }
        });
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.recharge("路人", 100);
        proxy.recharge("xiaomi", 23);
        
    
    }
    
    
}
