package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Demo5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(System.currentTimeMillis());
        //创建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);
        //将futureTask传递一个线程运行
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futureTask.run();
        }).start();
        System.out.println(System.currentTimeMillis());
        //futureTask.get()会阻塞当前线程，直到futureTask执行完毕
        Integer result = futureTask.get();
        System.out.println(System.currentTimeMillis() + ":" + result);


        /**
         * 创建了一个FutureTask对象，调用futureTask.get()会阻塞当前线程，子线程中休眠了3秒，
         * 然后调用futureTask.run();当futureTask的run()方法执行完毕之后，futureTask.get()会从阻塞中返回。
         *
         * 注意：这种方式和方式4的不同点。
         */
    }



}
