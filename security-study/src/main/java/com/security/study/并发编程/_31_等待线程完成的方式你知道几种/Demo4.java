package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.*;

public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        //创建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //将futureTask传递一个线程运行
        new Thread(futureTask).start();
        System.out.println(System.currentTimeMillis());
        //futureTask.get()会阻塞当前线程，直到futureTask执行完毕
        Integer result = futureTask.get();
        System.out.println(System.currentTimeMillis() + ":" + result);
        /**
         * 代码中使用FutureTask实现的，FutureTask实现了Runnable接口，并且内部带返回值，
         * 所以可以传递给Thread直接运行，futureTask.get()会阻塞当前线程，
         * 直到FutureTask构造方法传递的任务执行完毕，get方法才会返回。
         * 关于FutureTask详细使用， 请参考：JUC中的Executor框架详解1
         */
    }



}
