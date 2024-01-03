package com.security.study.swagger3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandlerKey;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class Swagger3Config {

    /**
     * 配置swagger的 Docket(摘要对象) 组1
     */
    @Bean
    public Docket createRestApi1() {
        return new Docket(DocumentationType.OAS_30) // 指定swagger3.0版本
                .enable(true) // 开关,一般swagger只在开发环境中开启（生产环境开启的话会有性能和安全问题），可以通过读取配置然后这里控制。
                .groupName("开发组1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.security.study.swagger3.aController")) // 指定需要使用swagger生成api文档的扫描的包，一般使用这种方式。
//                .paths(PathSelectors.ant("/swagger3Test/**")) // 匹配 /swagger3Test/** 请求路径
//                .paths(PathSelectors.none()) // 都不生成api
                .build()
                .apiInfo(createApiInfo1());
    }

    /**
     * 配置swagger的 Docket 组2
     */
    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .groupName("开发组2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.security.study.swagger3.bController"))
                .build()
                .apiInfo(createApiInfo2());
    }

    /**
     * 配置swagger的 ApiInfo 组1
     */
    @Bean
    public ApiInfo createApiInfo1() {
        return new ApiInfo("应急指挥",
                "应急指挥安全组项目",
                "1.2",
                "https://www.baidu.com",
                new Contact("马东亮", "https://www.baidu.com", "madongliang6699@163.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }


    /**
     * 配置swagger的 ApiInfo 组2
     */
    @Bean
    public ApiInfo createApiInfo2() {
        return new ApiInfo("应急指挥2",
                "应急指挥安全组项目2",
                "3.0",
                "https://www.baidu.com",
                new Contact("马东亮2", "https://www.baidu.com", "madongliang6699@163.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }


}
