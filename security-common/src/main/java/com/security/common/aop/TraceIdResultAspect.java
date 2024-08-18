package com.security.common.aop;

import com.security.common.core.JsonResult;
import com.security.common.filter.TraceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * traceId放进返回体JsonResult的切面
 */
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class TraceIdResultAspect {

    @Pointcut("execution(* com.security..controller..*.*(..)) || execution(* com.security.common.exception.GlobalExceptionHandler.*(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        if(proceed instanceof JsonResult){
            ((JsonResult<?>) proceed).setTraceId(TraceContext.getTraceId());
        }

        return proceed;
    }



}
