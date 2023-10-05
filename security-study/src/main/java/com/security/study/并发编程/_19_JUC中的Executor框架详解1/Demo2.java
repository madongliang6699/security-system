package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "执行结束");
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     *使用ScheduledThreadPoolExecutor的scheduleAtFixedRate方法，该方法设置了执行周期，下一次执行时间相当于是上一次的执行时间加上period，任务每次执行完毕之后才会计算下次的执行时间。
     *
     * 看一下这个方法的声明：
     *
     * public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
     *                                                   long initialDelay,
     *                                                   long period,
     *                                                   TimeUnit unit);
     * 4个参数：
     * command：表示要执行的任务
     * initialDelay：表示延迟多久执行第一次
     * period：连续执行之间的时间间隔
     * unit：参数2和参数3的时间单位，是个枚举，可以是天、小时、分钟、秒、毫秒、纳秒等
     *
     *
     * 代码中设置的任务第一次执行时间是系统启动之后延迟一秒执行。后面每次时间间隔1秒，
     * 从输出中可以看出系统启动之后过了1秒任务第一次执行（1、3行输出），输出的结果中可以看到任务第一次执行结束时间和
     * 第二次的开始时间一样，为什么会这样？前面有介绍，任务当前执行“完毕”之后会计算下次执行时间，
     * 下次执行时间为上次执行的开始时间+period，第一次开始执行时间是1564576405247，
     * 加1秒为1564576406247，这个时间小于第一次结束的时间了，说明小于系统当前时间了，会立即执行。
     * 【也就是说，period这个参数的时间计算是以每次的任务开始执行的那一刻时间点为依据计算的】
     * 【也就是说：就是设置的间隔是一秒钟执行一次，但是如果前一个任务执行了10秒钟的话，那下一个任务执行就直接被间隔了10秒，
     * 因此，这个方法虽然是多个线程执行任务，但是并起不到异步同时执行多个任务的效果，
     * 因为每次都是前一个任务执行完之后才计算下次执行的开始时间】
     */

}
