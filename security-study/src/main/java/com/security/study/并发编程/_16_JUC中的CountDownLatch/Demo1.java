package com.security.study.并发编程._16_JUC中的CountDownLatch;

import java.util.concurrent.TimeUnit;

public class Demo1 {

    public static class T extends Thread {
        //休眠时间（秒）
        int sleepSeconds;
        public T(String name, int sleepSeconds) {
            super(name);
            this.sleepSeconds = sleepSeconds;
        }
        @Override
        public void run() {
            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ",开始处理!");
            try {
                //模拟耗时操作，休眠sleepSeconds秒
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ",处理完毕,耗时:" + (endTime - startTime));
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        T t1 = new T("解析sheet1线程", 2);
        t1.start();
        T t2 = new T("解析sheet2线程", 5);
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时:" + (endTime - starTime));

        /**
         * 1696418106477,解析sheet1线程,开始处理!
         * 1696418106477,解析sheet2线程,开始处理!
         * 1696418108491,解析sheet1线程,处理完毕,耗时:2014
         * 1696418111487,解析sheet2线程,处理完毕,耗时:5010
         * 总耗时:5013
         *
         *
         * 代码中启动了2个解析sheet的线程，第一个耗时2秒，第二个耗时5秒，最终结果中总耗时：5秒。
         * 上面的关键技术点是线程的join()方法，此方法会让当前线程等待被调用的线程完成之后才能继续。
         * 可以看一下join的源码，内部其实是在synchronized方法中调用了线程的wait方法，
         * 最后被调用的线程执行完毕之后，由jvm自动调用其notifyAll()方法，唤醒所有等待中的线程。
         * 这个notifyAll()方法是由jvm内部自动调用的，jdk源码中是看不到的，
         * 需要看jvm源码，有兴趣的同学可以去查一下。
         * 所以JDK不推荐在线程上调用wait、notify、notifyAll方法。
         *
         * 而在JDK1.5之后的并发包中提供的CountDownLatch也可以实现join的这个功能。
         */

    }



}
