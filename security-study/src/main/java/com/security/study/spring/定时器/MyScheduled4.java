package com.security.study.spring.定时器;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

//@Component
public class MyScheduled4 {


    /**
     * 第三个惊呆：
     * cron表达式支持占位符
     *
     *<p/>
     * 另外小知识点：
     * 如果一个方法上设置了两个@Scheduled，那会产生两个定时任务在执行。和@Schedules注解的作用应该一样。
     * 如果业务的要求很刁钻，一个@Scheduled满足不了，就可以拆解需求，使用多个@Scheduled满足。
     */

//    @Scheduled(cron = "${time.cron}")  //time.cron是配置文件中的变量
//    @Scheduled(cron="*/${time.interval} * * * * *") //还支持这样的，cron表达式的一部分使用配置文件中的变量



//    //fixedDelay：上一次执行完毕时间点之后多长时间再执行。fixedDelayString是字符串形式的，支持占位符。
//    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行。(启动程序后立马执行，执行完毕后的时间点之后再5秒，开始执行下一次)
//
//    //fixedRate 上一次开始执行时间点之后多长时间再执行。fixedRateString是字符串形式的，支持占位符。
//    @Scheduled(fixedRate = 5000) //上一次开始执行时间点之后5秒再执行（启动程序后立马执行，执行开始的时间点之后再5秒，
                                // 开始执行下一次，如果执行的时间超过了5秒，那在执行完毕后立马开始执行下一次。
                                // （如果是异步多线程的情况下，还是执行完毕后再开始吗？））

    //initialDelay 延迟多长时间后再执行第一次 initialDelayString
//    @Scheduled(initialDelay = 10000, fixedRate = 1000)//启动后延迟10秒后执行第一次，之后按fixedRate的规则每5秒执行一次


    //这个注解不用多解释，看一下源码就知道作用了，当一个方法上面需要同时指定多个定时规则的时候，可以通过这个来配置
    @Schedules({@Scheduled(fixedRate = 500), @Scheduled(fixedRate = 1000)})



    public void aa() throws InterruptedException {
        System.out.println("start-----------"+System.currentTimeMillis() / 1000);
//        TimeUnit.SECONDS.sleep(10);
        System.out.println("end-----------"+System.currentTimeMillis() / 1000);
    }





}
