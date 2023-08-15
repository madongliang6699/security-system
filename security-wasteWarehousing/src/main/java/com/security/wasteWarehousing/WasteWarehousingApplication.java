package com.security.wasteWarehousing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.security.wasteWarehousing.domain")
//下面这个注解说是在springcloud比较新的版本上是不需要加这个注解了，只要pom.xml中引入了注册中心的依赖坐标，就默认开启注册发现，
// 如果想关闭注册发现可以配置spring.cloud.discovery.enabled=false
// @EnableDiscoveryClient
public class WasteWarehousingApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteWarehousingApplication.class, args);
        System.out.println("=========================================================");
        System.out.println("============ 启动 成功 ===================");
        System.out.println("=========================================================");
    }
}
