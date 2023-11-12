package com.security.study;

import com.security.study.spring.注解.Import.demo2.CacheType;
import com.security.study.spring.注解.Import.demo2.EnableMyCacheService;
import com.security.webSocket.CybstarWebSocketConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


// @EnableWebMvc
// @EnableCaching
// @EnableAsync
@SpringBootApplication
// @EnableAspectJAutoProxy
// @Import({MyUser.class, Service.class})
@EnableMyCacheService(type = CacheType.LOCAL)
//@MyMapperScan(basePackages = "com.mdl.Import.demo4")
// @MapperScan
//@ComponentScan
@Import({CybstarWebSocketConfigurer.class})
public class MyApplication {
    
    public static void main(String[] args) {
    
        // ConfigurationClassPostProcessor
    
        // ClassPathBeanDefinitionScanner //这个类可以扫描bean，并注入容器。
    
        SpringApplication.run(MyApplication.class, args);
        System.out.println("========================================");
        System.out.println("============   启动 成功   ===============");
        System.out.println("========================================");

    }
    
}
