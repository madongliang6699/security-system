package com.security.study.并发编程._21_java中的CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo1 {


    //访问次数
    static int count = 0;
    //模拟访问一次
    public static void request() throws InterruptedException {
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
     * main，耗时：126,count=968
     *
     * 代码中的count用来记录总访问次数，request()方法表示访问一次，内部休眠5毫秒模拟内部耗时，
     * request方法内部对count++操作。程序最终耗时1秒多，执行还是挺快的，但是count和我们期望的结果不一致，
     * 我们期望的是1000，实际输出的是973（每次运行结果可能都不一样）。
     *
     * 分析一下问题出在哪呢？
     *
     * 代码中采用的是多线程的方式来操作count，count++会有线程安全问题，count++操作实际上是由以下三步操作完成的：
     *
     * 获取count的值，记做A：A=count
     * 将A的值+1，得到B：B = A+1
     * 让B赋值给count：count = B
     * 如果有A、B两个线程同时执行count++，他们同时执行到上面步骤的第1步，得到的count是一样的，3步操作完成之后，
     * count只会+1，导致count只加了一次，从而导致结果不准确。
     */


}
