package com.security.study.并发编程._15_JUC中的Semaphore信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo1 {

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
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",释放许可!");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new T("t-" + i).start();
        }
    }

    /**
     *代码中new Semaphore(2)创建了许可数量为2的信号量，每个线程获取1个许可，同时允许两个线程获取许可，
     * 从输出中也可以看出，同时有两个线程可以获取许可，其他线程需要等待已获取许可的线程释放许可之后才能运行。
     * 为获取到许可的线程会阻塞在acquire()方法上，直到获取到许可才能继续。
     *
     */

}
