package com.mdl.common.config;

import com.mdl.common.bean.SpringApplicationContext;
import com.mdl.common.redis.RedisConfig;
import com.mdl.common.web.WebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Configuration
@Import(value = {WebConfiguration.class, RedisConfig.class, SpringApplicationContext.class})
public class CommonAutoConfiguration {

}