package com.security.study.spring.定时器;


import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

//@Component
public class MyScheduled2 {

    private static int cc = 0;
    private static int dd = 0;


    @Scheduled(fixedRate = 1000)
    public void cc() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"==cc=="+ cc++);
    }

    @Scheduled(fixedRateString = "1000")
    public void dd() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"==dd=="+ dd++);
    }



}
