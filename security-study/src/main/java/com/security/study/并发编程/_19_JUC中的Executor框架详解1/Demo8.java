package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;

public class Demo8 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",end!");
            return 10;
        });
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);
        result.cancel(false);
        System.out.println(result.isCancelled());
        System.out.println(result.isDone());
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());
        executorService.shutdown();
    }

    /**
     * 1696526203572,pool-1-thread-1,start!
     * true
     * true
     * 1696526208577,pool-1-thread-1,end!
     * 1696526209581,main
     * Exception in thread "main" java.util.concurrent.CancellationException
     * 	at java.util.concurrent.FutureTask.report(FutureTask.java:121)
     * 	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
     * 	at com.security.study.并发编程._19_JUC中的Executor框架详解1.Demo8.main(Demo8.java:22)
     *
     *任务执行中休眠了5秒，get方法获取执行结果，超时时间是3秒，3秒还未获取到结果，
     * get触发了TimeoutException异常，当前线程从阻塞状态苏醒了。输出2个true，表示任务已被取消，已完成，
     * 取消之后调用get方法会触发CancellationException异常。
     */

}
