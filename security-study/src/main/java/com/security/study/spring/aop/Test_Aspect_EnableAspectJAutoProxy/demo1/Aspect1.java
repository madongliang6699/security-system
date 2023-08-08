package com.security.study.spring.aop.Test_Aspect_EnableAspectJAutoProxy.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 通过Aspect来定义一个前置通知，需要拦截上面2个bean的所有方法，在方法执行之前输出一行日志
 */
@Component //@1 使用 @Component 将这个类注册到spring容器；
@Aspect //@2 使用 @Aspect 标注着是一个 AspectJ 来定义通知的配置类；
public class Aspect1 {
    
    
    @Pointcut("execution(* com.mdl.aop.Test_Aspect_EnableAspectJAutoProxy.demo1..say(..))") //@3定义切入点，目前的配置，会拦截test1包及其子包中所有类的所有say方法，而CarService和UserService刚好满足，所以会被拦截；
    public void pc() {
    }
    
    
    @Before("pc()") //@4 定义一个前置通知，这个通知会对@3定义的切入点起效；
    public void before(JoinPoint joinPoint) {
        System.out.println("@Before通知!");
        // System.out.println("我是前置通知,target:" + joinPoint.getTarget()); //@5 目标方法执行执行，输出一行日志；
    }
    
    // @After("@annotation(Mdl)")
    // public void aa(JoinPoint joinPoint) {
    //     System.out.println("我是后置通知-----====,target:" + joinPoint.getTarget());
    // }
    
    
    @Around("pc()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("@Around通知start");
        Object result = joinPoint.proceed();
        System.out.println("@Around绕通知end");
        return result;
    }
    @After("pc()")
    public void after() throws Throwable {
        System.out.println("@After通知!");
    }
    @AfterReturning("pc()")
    public void afterReturning() throws Throwable {
        System.out.println("@AfterReturning通知!");
    }
    @AfterThrowing("pc()")
    public void afterThrowing() {
        System.out.println("@AfterThrowing通知!");
    }
    
    
}
