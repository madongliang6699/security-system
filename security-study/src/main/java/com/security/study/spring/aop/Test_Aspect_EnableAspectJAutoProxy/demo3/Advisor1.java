package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.stereotype.Component;

@Component
public class Advisor1 extends DefaultPointcutAdvisor {
    
    public Advisor1() {
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("Advisor1 start");
                Object result = invocation.proceed();
                System.out.println("Advisor1 end");
                return result;
            }
        };
        //设置拦截器
        setAdvice(methodInterceptor);
        
        //设置执行顺序
        setOrder(4);
    
        AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
        expressionPointcut.setExpression("execution(* com.mdl.aop.Test_Aspect_EnableAspectJAutoProxy.demo3.Service3.*(..))");
        //设置切点
        setPointcut(expressionPointcut);
    }
    
    
    
    
}
