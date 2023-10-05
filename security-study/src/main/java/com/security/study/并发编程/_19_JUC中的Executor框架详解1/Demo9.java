package com.security.study.并发编程._19_JUC中的Executor框架详解1;

import java.util.concurrent.*;

public class Demo9 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",start!");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()+",end!");
            return 10;
        });

        System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName());

        new Thread(futureTask).start();

        System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",结果:"+futureTask.get());
    }

    /**
     * 1696526404173,main
     * 1696526404173,main
     * 1696526404173,Thread-0,start!
     * 1696526409189,Thread-0,end!
     * 1696526404173,main,结果:10
     *
     * 大家可以回过头去看一下上面用线程池的submit方法返回的Future实际类型正是FutureTask对象，有兴趣的可以设置个断点去看看。
     * FutureTask类还是相当重要的，标记一下。
     */

}
