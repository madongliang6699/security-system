package com.security.study.并发编程._18_线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,
                                                                6,
                                                                10,
                                                                TimeUnit.SECONDS,
                                                                new ArrayBlockingQueue<>(10),
                                                                Executors.defaultThreadFactory(),
                                                                new ThreadPoolExecutor.AbortPolicy()
                                                                );

    public static void main(String[] args) {

        for (int i = 0; i < 12; i++) {
            int j = i;
            String taskName = "任务" + j;
            poolExecutor.execute(() -> {
                //模拟任务内部处理耗时
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "-" + Thread.currentThread().getName() + taskName + "处理完毕");
            });
        }
        //关闭线程池
        poolExecutor.shutdown();
        System.out.println("=== main end ====================");
    }

    /**
     * 从打印的信息中可以看出，通过最大线程数部分的线程执行的任务，确实先于队列中任务执行。
     *
     * 另外，如果把任务数量加到到20个，就会触发饱和策略，报异常，报异常之后就无法执行main线程的关闭线程池和最后打印行了，
     * 这很有问题，如果线上有这种问题怎么办，看来饱和策略一定要小心设计，不能抛异常。
     */





}
