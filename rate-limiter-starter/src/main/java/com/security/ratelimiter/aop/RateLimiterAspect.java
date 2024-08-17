package com.security.ratelimiter.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.security.ratelimiter.config.RateLimiterProperties;
import com.security.ratelimiter.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final RateLimiterProperties rateLimiterProperties;
    //缓存每个接口方法的RateLimiter对象
    private final ConcurrentMap<String, RateLimiter> limitersMap = new ConcurrentHashMap<>();

    @Autowired
    public RateLimiterAspect(RateLimiterProperties rateLimiterProperties) {
        this.rateLimiterProperties = rateLimiterProperties;
    }

    // 定义切点，匹配所有Controller中的方法
    @Pointcut("execution(* com.security..controller..*.*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        //通过aop，拿到添加了RateLimit注解的方法的名称（例如："com.example.service.UserService.getUser(int)"）
        String key = joinPoint.getSignature().toShortString();

        //给指定的方法获取对应的已缓存的RateLimiter对象，如果还没有缓存，就创建一个再缓存，创建的RateLimiter对象中被设置了每秒最大请求数，这样，就间接给该接口设置了最大请求数
        RateLimiter rateLimiter = getRateLimiter(joinPoint, key);

        //从RateLimiter对象中获取令牌，RateLimiter对象根据在每秒时间内调用tryAcquire()方法的次数，与设置好的最大请求数做比较，如果调用次数超过最大次数，返回false
        if (!rateLimiter.tryAcquire()) {
            throw new RuntimeException("访问频率过高，超过每秒最大限制数：" + rateLimiter.getRate());
        }

        return joinPoint.proceed();
    }

    private RateLimiter getRateLimiter(ProceedingJoinPoint joinPoint, String key) {
        return limitersMap.computeIfAbsent(key, k -> {
            RateLimit rateLimit = getRateLimitAnnotation(joinPoint);
            //如果切点上的rateLimit注解不是null，使用注解上的参数，如果注解是null，使用rateLimiterProperties配置的参数
            if (rateLimit != null && rateLimit.value() > 0) {
                return RateLimiter.create(rateLimit.value());
            } else {
                return RateLimiter.create(rateLimiterProperties.getDefaultLimit());
            }
        });
    }

    private RateLimit getRateLimitAnnotation(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        //如果切点匹配上的是请求方法，那先判断该方法上是否有RateLimit注解，如果有就使用方法上的注解
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            RateLimit annotation = methodSignature.getMethod().getAnnotation(RateLimit.class);
            if (annotation != null) {
                return annotation;
            }
        }
        //如果不是方法或者方法上没有注解，就返回切点的类上的RateLimit注解（当前如果类上也没注解，返回的是null）
        return joinPoint.getTarget().getClass().getAnnotation(RateLimit.class);
    }

}
