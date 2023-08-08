package com.security.common.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.common.core.DateProvider;
import com.security.common.core.DateProviderImpl;
import com.security.common.core.ObjectMapperImpl;
import com.security.common.exception.GlobalExceptionHandler;
import com.security.common.json.JsonExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * web相关bean组件配置
 *
 * @author madongliang
 * @version 1.0
 */
@Configuration
@Import(value = {GlobalExceptionHandler.class, GlobalResponseBodyAdvice.class})
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImpl();
    }

    @Bean
    public DateProvider dateProvider() {
        return new DateProviderImpl();
    }

    @Bean
    public JsonExtractor jsonExtractor() {
        return new JsonExtractor();
    }
}