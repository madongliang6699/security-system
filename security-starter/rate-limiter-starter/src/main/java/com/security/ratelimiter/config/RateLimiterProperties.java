package com.security.ratelimiter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 限流的属性配置类
 */
@ConfigurationProperties(prefix = "ratelimiter")
public class RateLimiterProperties {

    // 默认每秒允许的请求数
    private int defaultLimit = 1;

    public int getDefaultLimit() {
        return defaultLimit;
    }

    public void setDefaultLimit(int defaultLimit) {
        this.defaultLimit = defaultLimit;
    }

}
