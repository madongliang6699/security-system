package com.security.study.spring.注解.Import.demo3;

import com.security.study.spring.注解.Import.demo1.MyImportTestUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(MyImportBeanRegister.class)
@Configuration
public class Config11 {
    
    /**
     * 这里@bean输入MyImportTestUser对象和 MyImportBeanRegister 里面注入MyImportTestUser对象是不冲突的，只要bean的名字不一样就行。
     *
     */
    @Bean
    public MyImportTestUser myImportTestUser1(){
        MyImportTestUser myImportTestUser = new MyImportTestUser();
        myImportTestUser.setName("小花");
        return myImportTestUser;
    }
    
}
