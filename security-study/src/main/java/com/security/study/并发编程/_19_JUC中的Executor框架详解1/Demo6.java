package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> result = executorService.submit(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",end!");
            return 10;
        });

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());

        executorService.shutdown();
    }

    /**
     * 1696525321336,main
     * 1696525321336,pool-1-thread-1,start!
     * 1696525326345,pool-1-thread-1,end!
     * 1696525321336,main,结果：10
     *
     *代码中创建了一个线程池，调用线程池的submit方法执行任务，submit参数为Callable接口：表示需要执行的任务有返回值，
     * submit方法返回一个Future对象，Future相当于一个凭证，可以在任意时间拿着这个凭证去获取对应任务的
     * 执行结果（调用其get方法），代码中调用了result.get()方法之后，此方法会阻塞当前线程直到任务执行结束。
     */

}
