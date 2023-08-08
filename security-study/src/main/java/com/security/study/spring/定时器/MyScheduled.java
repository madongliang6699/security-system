package com.security.study.spring.定时器;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;
//@Component
public class MyScheduled {

    private static int aa = 0;
    private static int bb = 0;

    /**
     * 说明：
     * 从 MyScheduled类和 MyScheduled2类里面的4个定时器方法的执行可以看出，虽然设置的都是美秒执行一次，在里面睡眠5秒后，
     * 每个定时器都变成了每5秒执行一次，这个在意料之中，
     * 没想到的是，从打印的线程名字和实际执行的结果可以看出：这4个定时器方法都是同一个线程在执行，也就是每个定时器方法不是自己单独的线程在执行，
     * 4个方法执行一遍需要20秒。惊呆了。
     * 这个和之前的认知是不同的，以前以为每个定时器方法都是一个独立的线程在执行。怪不得推送可视化的数据到kafka需要那么长的时间，
     * 因为有9个定时器，每个定时器里有若干个表，一共31个表，每个表都有很多数据需要分页查询多次去推动到kafka，
     * 31个表的整个执行过程都是一个线程在同步执行。并且每个表发出deleteAll命令后都睡眠10秒钟后推送数据。光睡眠时间都5分钟了。
     *
     * @throws InterruptedException
     */

    @Scheduled(fixedRate = 1000)
    public void aa() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"==aa=="+ aa++);
    }

    @Scheduled(fixedRateString = "1000")
    public void bb() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"==bb=="+ bb++);
    }





}
