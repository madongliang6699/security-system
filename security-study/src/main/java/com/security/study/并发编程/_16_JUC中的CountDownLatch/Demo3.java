package com.security.study.并发编程._16_JUC中的CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo3 {
    public static class T extends Thread {
        //休眠时间（秒）
        int sleepSeconds;
        CountDownLatch countDownLatch;

        public T(String name, int sleepSeconds, CountDownLatch countDownLatch) {
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
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
            } finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ",处理完毕,耗时:" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start!");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long starTime = System.currentTimeMillis();
        T t1 = new T("解析sheet1线程", 2, countDownLatch);
        t1.start();
        T t2 = new T("解析sheet2线程", 5, countDownLatch);
        t2.start();
        boolean result = countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 end!");
        long endTime = System.currentTimeMillis();
        System.out.println("主线程耗时:" + (endTime - starTime) + ",result:" + result);
    }

    /**
     * 1696419324577,main线程 start!
     * 1696419324579,解析sheet1线程,开始处理!
     * 1696419324579,解析sheet2线程,开始处理!
     * 1696419326592,解析sheet1线程,处理完毕,耗时:2013
     * 1696419326592,main线程 end!
     * 主线程耗时:2015,result:false
     * 1696419329582,解析sheet2线程,处理完毕,耗时:5003
     *
     *
     * 从输出结果中可以看出，线程2耗时了5秒，主线程耗时了2秒，
     * 主线程中调用countDownLatch.await(2, TimeUnit.SECONDS);，表示最多等2秒，不管计数器是否为0，await方法都会返回，
     * 若等待时间内，计数器变为0了，立即返回true，否则超时后返回false。
     */

}
