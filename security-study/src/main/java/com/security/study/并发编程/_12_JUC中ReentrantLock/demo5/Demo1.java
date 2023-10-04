package com.security.study.并发编程._12_JUC中ReentrantLock.demo5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    private static ReentrantLock lock = new ReentrantLock(false);


    public static class T1 extends Thread {

        public T1(String name) {
            super(name);
        }
        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + ":" + this.getName() + "开始获取锁!");
                //尝试获取锁，不论是否成功，立即返回，不会阻塞
                if (lock.tryLock()) {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "获取到了锁!");
                    //获取到锁之后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "未能获取到锁!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        /**
         *
         */

        Thread t1 = new T1("t1");
        Thread t2 = new T1("t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();//这个方法是线程自带的方法，ReentrantLock里面肯定是利用了这个方法的机制。

        /**
         * 代码中获取锁成功之后，休眠5秒，会导致另外一个线程获取锁失败，运行代码，输出：
         *
         * 1563356291081:t2开始获取锁!
         * 1563356291081:t2获取到了锁!
         * 1563356291081:t1开始获取锁!
         * 1563356291081:t1未能获取到锁!
         *
         * 可以看到t2获取成功，t1获取失败了，tryLock()是立即响应的，中间不会有阻塞。
         *
         *
         *
         * tryLock有参方法:
         * 可以明确设置获取锁的超时时间，该方法签名：
         * public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException
         *
         * 该方法在指定的时间内不管是否可以获取锁，都会返回结果，返回true，表示获取锁成功，返回false表示获取失败。
         * 此方法有2个参数，第2个参数是时间类型，是一个枚举，可以表示时、分、秒、毫秒等待，使用比较方便，
         * 第1个参数表示在时间类型上的时间长短。此方法在执行的过程中，如果调用了线程的中断interrupt()方法，
         * 会触发InterruptedException异常。
         *
         * 关于tryLock()方法和tryLock(long timeout, TimeUnit unit)方法，说明一下：
         * 1、都会返回boolean值，结果表示获取锁是否成功
         * 2、tryLock()方法，不管是否获取成功，都会立即返回；而有参的tryLock方法会尝试在指定的时间内去获取锁，中间会有阻塞的现象，在指定的时间之后会不管是否能够获取锁都会返回结果
         * 3、tryLock()方法不会响应线程的中断方法；而有参的tryLock方法会响应线程的中断方法，而触发InterruptedException异常，这个从2个方法的声明上可以可以看出来
         */

    }
}
