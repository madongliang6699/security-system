package com.security.study.并发编程._16_JUC中的CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo2 {

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
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 end!");
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时:" + (endTime - starTime));
    }

    /**
     * 1696419104620,main线程 start!
     * 1696419104622,解析sheet2线程,开始处理!
     * 1696419104622,解析sheet1线程,开始处理!
     * 1696419106623,解析sheet1线程,处理完毕,耗时:2001
     * 1696419109625,解析sheet2线程,处理完毕,耗时:5003
     * 1696419109625,main线程 end!
     * 总耗时:5004
     *
     *
     * 从结果中看出，效果和join实现的效果一样，代码中创建了计数器为2的CountDownLatch，
     * 主线程中调用countDownLatch.await();会让主线程等待，t1、t2线程中模拟执行耗时操作，
     * 最终在finally中调用了countDownLatch.countDown();,
     * 此方法每调用一次，CountDownLatch内部计数器会减1，
     * 当计数器变为0的时候，主线程中的await()会返回，然后继续执行。
     *
     * 注意：上面的countDown()这个是必须要执行的方法，所以放在finally中执行。
     */

}
