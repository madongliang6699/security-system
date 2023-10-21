package com.security.study.并发编程._29_JUC中的CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.System.out;

public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                out.println(Thread.currentThread().getName() + "线程，result1 = " + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                out.println("t=" + t);//t是上面get方法的返回值
                long result = t * 5;
                out.println(Thread.currentThread().getName() + "线程，result2=" + result);
                return result;
            }
        });


        long result = longCompletableFuture.get();
        System.out.println(Thread.currentThread().getName() + "线程，最后result:" + result);

        /**
         * 第二个任务依赖第一个任务的结果。
         */

    }
}
