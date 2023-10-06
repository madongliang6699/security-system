package com.security.study.并发编程._21_java中的CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo2 {


    //访问次数
    static int count = 0;
    //模拟访问一次
    public static synchronized void request() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
    }

    /**
     * main，耗时：5673,count=1000
     *
     * 程序中request方法使用synchronized关键字，保证了并发情况下，request方法同一时刻只允许一个线程访问，
     * request加锁了相当于串行执行了，count的结果和我们预期的结果一致，只是耗时比较长，5秒多。
     */


}
