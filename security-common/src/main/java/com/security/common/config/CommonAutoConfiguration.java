package com.security.common.config;

import com.security.common.aop.TraceIdResultAspect;
import com.security.common.bean.SpringApplicationContext;
import com.security.common.filter.TraceIdFilter;
import com.security.common.redis.RedisConfig;
import com.security.common.web.WebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author madongliang
 * @version 1.0
 */
@Configuration
@Import(value = {WebConfiguration.class,
        RedisConfig.class,
        SpringApplicationContext.class,
        MyBatisPlusConfig.class,
        TraceIdFilter.class,
        TraceIdResultAspect.class
})
public class CommonAutoConfiguration {

}