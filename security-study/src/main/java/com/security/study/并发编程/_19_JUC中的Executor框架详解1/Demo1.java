package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            System.out.println(System.currentTimeMillis() + "开始执行");
            //模拟任务耗时
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "执行结束");
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
     *
     * 这个方法3个参数：
     * command：需要执行的任务
     * delay：需要延迟的时间
     * unit：参数2的时间单位，是个枚举，可以是天、小时、分钟、秒、毫秒、纳秒等
     */

}
