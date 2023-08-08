package com.security.study.spring.事件机制;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 发待办监听器
 */
@Component
public class DaiBanListener /*implements ApplicationListener<DaiBanEvent>*/ {


//    @Async
    @Order(0) //todo 使用异步之后，排序就不起作用了。
    @EventListener //todo 实现监听器的方法有两种：
                    // 一种是面向接口，就是实现上面的ApplicationListener接口以及其onApplicationEvent方法，
                    // 另一种就是使用注解@EventListener
    public void aaa(DaiBanEvent event) {
        try {
            Thread.sleep(4000);
            System.out.println("当前线程名字："+Thread.currentThread().getName());
        } catch (InterruptedException e) {}

        System.out.println("发送了待办: "+event.getDaiBanBody());
    }


//    @Async
    @EventListener
    @Order(1)
    public void bbb(DaiBanEvent event) {
        try {
            Thread.sleep(3000);
            System.out.println("当前线程名字："+Thread.currentThread().getName());
        } catch (InterruptedException e) {}

        System.out.println("发送了mes消息: "+event.getDaiBanBody());
    }

//    @Override
//    public void onApplicationEvent(DaiBanEvent event) {
//        try {
//            Thread.sleep(1000);
//            System.out.println("当前线程名字："+Thread.currentThread().getName());
//        } catch (InterruptedException e) {}
//
//        System.out.println("发送了移动端消息: "+event.getDaiBanBody());
//    }
}
