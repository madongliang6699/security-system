package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.*;

public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(System.currentTimeMillis());
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //关闭线程池
        executorService.shutdown();
        System.out.println(System.currentTimeMillis());
        Integer result = future.get();
        System.out.println(System.currentTimeMillis() + ":" + result);

        /**
         * 使用ExecutorService.submit方法实现的，
         * 此方法返回一个Future，future.get()会让当前线程阻塞，直到Future关联的任务执行完毕。
         */
    }



}
