package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            System.out.println(10 / 0);
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "执行结束");
        }, 1, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5);

        //isCancelled()判断定时器是否被取消执行了（取消方法Demo5讲，异常导致无法继续执行的，不属于取消）
        System.out.println(scheduledFuture.isCancelled());

        //isDone() 判断是否已经执行完毕了，异常导致的无法继续执行，也算执行完毕了。
        System.out.println(scheduledFuture.isDone());

        //scheduledExecutorService.shutdown();
    }

    /**
     *1696521867364
     * pool-1-thread-1
     * 1696521868415第1次开始执行
     * false
     * true
     *
     * 【从上面的打印看出，只要有异常，任务就不再执行了，下一次任务以及之后的任务也不再执行了。】
     *
     * 先说补充点知识：schedule、scheduleAtFixedRate、scheduleWithFixedDelay这几个方法有个返回值ScheduledFuture，
     * 通过ScheduledFuture可以对执行的任务做一些操作，如判断任务是否被取消、是否执行完成。
     *
     * 再回到上面代码，任务中有个10/0的操作，会触发异常，发生异常之后没有任何现象，
     * 被ScheduledExecutorService内部给吞掉了，然后这个任务再也不会执行了，
     * scheduledFuture.isDone()输出true，表示这个任务已经结束了，再也不会被执行了。
     * 所以如果程序有异常，开发者自己注意处理一下，不然跑着跑着发现任务怎么不跑了，也没有异常输出。
     */

}
