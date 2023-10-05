package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;

public class Demo7 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> result = executorService.submit(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",end!");
            return 10;
        });

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        try {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get(3,TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "不等了,去做其他事情了");

        executorService.shutdown();
    }

    /**
     * 1696525757249,main
     * 1696525757249,pool-1-thread-1,start!
     * java.util.concurrent.TimeoutException
     * 	at java.util.concurrent.FutureTask.get(FutureTask.java:205)
     * 	at com.security.study.并发编程._19_JUC中的Executor框架详解1.Demo7.main(Demo7.java:19)
     * 1696525760267,main不等了,去做其他事情了
     * 1696525762263,pool-1-thread-1,end!
     *
     *任务执行中休眠了5秒，get方法获取执行结果，超时时间是3秒，3秒还未获取到结果，
     * get触发了TimeoutException异常，当前线程从阻塞状态苏醒了。
     */

}
