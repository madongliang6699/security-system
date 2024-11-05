package com.security.order.other.mq;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
public class RocketMQConfig {

}
