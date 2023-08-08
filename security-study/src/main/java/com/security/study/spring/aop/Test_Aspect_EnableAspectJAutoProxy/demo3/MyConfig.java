package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    
    
    
    @Bean
    public DefaultPointcutAdvisor Advisor2() {
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("Advisor2 start");
                Object result = invocation.proceed();
                System.out.println("Advisor2 end");
                return result;
            }
        };
        
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.mdl.aop.Test_Aspect_EnableAspectJAutoProxy.demo3.Service3.*(..))");
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(methodInterceptor);
        advisor.setOrder(2);
        return advisor;
    }
    
    
}
