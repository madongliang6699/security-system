package com.security.study.spring.事件机制;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DaiBanController /*implements ApplicationEventPublisherAware*/ {

    @Autowired //todo 使用@Autowired自动注入也可以，使用上面的implements ApplicationEventPublisherAware实现接口的方式也可以
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/a")
    public String health(Integer a, String dd) {

        System.out.println("--------开始--------------");
        //eventPublisher.publishEvent(new DaiBanEvent(this, dd));
        eventPublisher.publishEvent(new DaiBanEvent(){{setDaiBanBody(dd);}});
        System.out.println("--------结束--------------");

        return "地方";
    }

//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.eventPublisher = applicationEventPublisher;
//    }
}
