package com.security.study.spring.注解.Import.demo1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(MyImportSelector.class)
// @Component  //这里要加上@Configuration注解或者其他能实例化bean的注解，因为@Import本身不具备实例化的功能。或者把@Import直接放启动类上也行
@Configuration
public class MyImportConfig {
}
