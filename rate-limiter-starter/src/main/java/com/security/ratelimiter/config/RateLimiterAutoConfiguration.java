package com.security.ratelimiter.config;


import com.security.ratelimiter.aop.RateLimiterAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个配置类，如果放在 spring.factories 文件里，就可以自动被自动装配，如果不放在spring.factories文件里，
 * 那springboot不会自动装配，不过可以通过在启动类上手动引入@Import(RateLimiterAutoConfiguration.class)，做到手动装配。
 * 当然装配的时候，如果下面@ConditionalOnProperty注解里的条件不满足，也是不会实例化该配置类的。
 */
@Configuration
//该注解是指定加载该配置类的条件（配置文件中的“ratelimiter.enabled”参数为true，matchIfMissing表示如果不配置该参数默认是否加载该配置类），如果条件满足就实例化该配置类，并实例化里面的@Bean，
//@ConditionalOnProperty是@Conditional注解的一个特例
@ConditionalOnProperty(name = "ratelimiter.enabled", havingValue = "true", matchIfMissing = true)
public class RateLimiterAutoConfiguration {

    @Bean
    public RateLimiterProperties rateLimiterProperties() {
        return new RateLimiterProperties();
    }

    @Bean
    public RateLimiterAspect rateLimiterAspect(RateLimiterProperties properties) {
        return new RateLimiterAspect(properties);
    }

}
