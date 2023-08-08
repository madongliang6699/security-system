package com.security.study.spring.注解.Import.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCacheImportController {
    
   @Autowired
   CacheService cacheService;
    
    @RequestMapping(value = "/dd")
    public String cc(Integer a, String dd) {
        
        cacheService.set("11", "aa");
        cacheService.get("11");
        
        return "地方";
    }
    
}
