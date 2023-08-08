package com.security.study.特定知识点测试和总结.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AAService {
    
    @Autowired
    RunUtils runUtils;
    
    /**
     * @Async 注解使用注意:
     * 只能使用于spring管理的bean中;
     * 不能在同一个类中调用该注解的方法;
     * 使用该注解会有循环依赖的问题;需要是要@Lazy注解解决;
     * 好像默认实现异步的方式,是新建一个线程处理,而不是使用线程池,这样高并发的情况下会导致线程过多.需要初始化一个线程池供他使用.
     *
     *
     *
     * @return
     */
    
    
    //@Async
    public String aa(){
        runUtils.runByAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            
            }
            System.out.println("hahah");
        }, "sssss");
        System.out.println("over");
        return "啊啊";
    }
    

}
