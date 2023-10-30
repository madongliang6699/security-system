package com.security.study.并发编程._30_JUC中的CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //没有返回值的异步方法
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("run end ...");
        });

        System.out.println("--------------");
        future.get();//阻塞
        System.out.println("==============");

        //有返回值的
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("run end ...");
            return 11L;
        });
        System.out.println("--------------");
        Long l = longCompletableFuture.get();//阻塞
        System.out.println(l);
        System.out.println("==============");

    }
}
