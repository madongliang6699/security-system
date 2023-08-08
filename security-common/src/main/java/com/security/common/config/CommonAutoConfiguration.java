package com.security.common.config;

import com.security.common.bean.SpringApplicationContext;
import com.security.common.redis.RedisConfig;
import com.security.common.web.WebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author madongliang
 * @version 1.0
 */
@Configuration
@Import(value = {WebConfiguration.class, RedisConfig.class, SpringApplicationContext.class})
public class CommonAutoConfiguration {

}