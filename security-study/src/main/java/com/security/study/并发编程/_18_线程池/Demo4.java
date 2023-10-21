package com.security.study.并发编程._18_线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo4 {

    static AtomicInteger threadNum = new AtomicInteger(1);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("处理订单过期支付的线程-" + threadNum.getAndIncrement());
                    return thread;
                }
        );
        for (int i = 0; i < 5; i++) {
            String taskName = "任务-" + i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
            });
        }
//        executor.shutdown();
    }

    /**
     * 代码中在任务中输出了当前线程的名称，可以看到是我们自定义的名称。
     *
     * 通过jstack查看线程的堆栈信息，也可以看到我们自定义的名称，我们可以将代码中executor.shutdown();
     * 先给注释掉让程序先不退出，然后通过jstack查看，如下执行栈：
     *
     */

}