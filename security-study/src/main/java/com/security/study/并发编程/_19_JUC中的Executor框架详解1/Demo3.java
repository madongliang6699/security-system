package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "执行结束");
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     *使用ScheduledThreadPoolExecutor的scheduleWithFixedDelay方法，该方法设置了执行周期，
     * 与scheduleAtFixedRate方法不同的是，下一次执行时间是上一次任务执行完的系统时间加上period，
     * 因而具体执行时间不是固定的，但周期是固定的，是采用相对固定的延迟来执行任务。看一下这个方法的声明：
     * public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
     *                                                      long initialDelay,
     *                                                      long delay,
     *                                                      TimeUnit unit);
     *
     * 4个参数：
     * command：表示要执行的任务
     * initialDelay：表示延迟多久执行第一次
     * period：表示下次执行时间和上次执行结束时间之间的间隔时间
     * unit：参数2和参数3的时间单位，是个枚举，可以是天、小时、分钟、秒、毫秒、纳秒等
     *
     * 假设系统调用scheduleAtFixedRate的时间是T1，那么执行时间如下：
     * 第1次：T1+initialDelay，执行结束时间：E1
     * 第2次：E1+period，执行结束时间：E2
     * 第3次：E2+period，执行结束时间：E3
     * 第4次：E3+period，执行结束时间：E4
     * 第n次：上次执行结束时间+period
     *
     *打印：
     * 1696521647917
     * pool-1-thread-1
     * 1696521648988第1次开始执行
     * 1696521650989第1次执行结束
     * pool-1-thread-1
     * 1696521654001第2次开始执行
     * 1696521656017第2次执行结束
     * pool-1-thread-2
     * 1696521659027第3次开始执行
     * 1696521661029第3次执行结束
     * pool-1-thread-1
     * 1696521664035第4次开始执行
     * 1696521666037第4次执行结束
     *
     * 延迟1秒之后执行第1次，后面每次的执行时间和上次执行结束时间间隔3秒。
     *
     * scheduleAtFixedRate和scheduleWithFixedDelay示例建议多看2遍。
     *
     * 【这个方法也起不到异步同时执行多个任务的效果】
     */

}
