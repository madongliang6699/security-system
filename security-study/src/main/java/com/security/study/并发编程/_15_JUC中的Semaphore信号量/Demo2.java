package com.security.study.并发编程._15_JUC中的Semaphore信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo2 {

    //共有两个许可，也就是：如果每个线程只能获取一个许可，那同一时刻只能有两个线程拿到许可进行工作
    static Semaphore semaphore = new Semaphore(2);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            try {
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可!");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",运行结束!");
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",当前可用许可数量:" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new T("t-" + i).start();
        }
    }

    /**
     * 1696329714240,t-2,获取许可!
     * 1696329714240,t-0,获取许可!
     * 1696329717258,t-2,运行结束!
     * 1696329717259,t-2,当前可用许可数量:0
     * 1696329717260,t-0,运行结束!
     * 1696329717262,t-0,当前可用许可数量:0
     *
     * 上面程序运行后一直无法结束，观察一下代码，代码中获取许可后，没有释放许可的代码，
     * 最终导致，可用许可数量为0，其他线程无法获取许可，会在semaphore.acquire();处等待，
     * 导致程序无法结束。
     */

}
