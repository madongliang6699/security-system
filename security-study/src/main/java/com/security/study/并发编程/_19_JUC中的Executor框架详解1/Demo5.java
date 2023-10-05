package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "执行结束");
        }, 1, 1, TimeUnit.SECONDS);
//        TimeUnit.SECONDS.sleep(5);

        TimeUnit.SECONDS.sleep(13);

        //cancel(false)方法是取消任务执行，取消之后会把当前正在执行的任务继续执行完毕，然后就不执行了。不执行之后别忘了shutdown()线程池。
        //cancel(true)方法是取消任务执行，取消之后也是能把当前任务继续执行完毕，然后不再执行，但是会报打断的异常。不知道有什么用。
        scheduledFuture.cancel(false);

        System.out.println("任务是否被取消："+scheduledFuture.isCancelled());
        System.out.println("任务是否已完成："+scheduledFuture.isDone());

//        scheduledExecutorService.shutdown();
    }

    /**
     * 1696524030269
     * pool-1-thread-1
     * 1696524031354第1次开始执行
     * 1696524035363第1次执行结束
     * pool-1-thread-1
     * 1696524036366第2次开始执行
     * 1696524040367第2次执行结束
     * pool-1-thread-1
     * 1696524041390第3次开始执行
     * 任务是否被取消：true
     * 任务是否已完成：true
     * 1696524045401第3次执行结束
     *
     *
     */

}
