package com.security.study.spring.定时器;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

//@Component
public class MyScheduled5 {

    private static int aa = 0;
    private static int bb = 0;

    /**
     * 说明：
     *
     */

    @Scheduled(fixedRate = 1000)
    public void aa() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+"==aa=="+ aa++);
    }

    @Scheduled(fixedRateString = "1000")
    public void bb() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+"==bb=="+ bb++);
    }

    @Scheduled(fixedRateString = "5000")
    public void cc() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"=========cc=="+ bb++);
    }


    @Scheduled(fixedRateString = "5000")
    public void dd() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"=========cc=="+ bb++);
    }


}
