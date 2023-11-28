package com.security.wasteGeneration;

import org.mybatis.spring.annotation.MapperScan;
import com.security.common.jwt.config.InterceptorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.security.wasteGeneration.domain.mapper")
@Import(InterceptorConfig.class)
public class WasteGenerationApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WasteGenerationApplication.class, args);
        System.out.println("=========================================================");
        System.out.println("============ 启动 成功 ===================");
        System.out.println("=========================================================");
    }
    
}
