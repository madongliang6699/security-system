package com.security.study.spring.注解.Import.demo4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyImportController22 {
    
    //@Autowired
    //UserMapper userMapper;
    
    // @Autowired
    // OrgMapper orgMapper; //这里使用interface接口，是没法注册到容器中的，而mybatis可以通过接口注入其实现类，背后肯定做了特殊处理。
    
    
    @RequestMapping(value = "/ff")
    public String cc(Integer a, String dd) {
    
        //userMapper.addUser();
        
        // orgMapper.addOrg();
        
        return "地方";
    }
    
}
