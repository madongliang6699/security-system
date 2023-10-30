package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(System.currentTimeMillis());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println(System.currentTimeMillis());
        //completableFuture.get()会阻塞当前线程，直到completableFuture执行完毕
        Integer result = completableFuture.get();
        System.out.println(System.currentTimeMillis() + ":" + result);


        /**
         * CompletableFuture.supplyAsync可以用来异步执行一个带返回值的任务，调用completableFuture.get()
         *
         * 会阻塞当前线程，直到任务执行完毕，get方法才会返回。
         */
    }


}
