package com.security.wasteWarehousing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.security.wasteWarehousing.domain")
public class WasteWarehousingApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteWarehousingApplication.class, args);
        System.out.println("=========================================================");
        System.out.println("============ 启动 成功 ===================");
        System.out.println("=========================================================");
    }
}
