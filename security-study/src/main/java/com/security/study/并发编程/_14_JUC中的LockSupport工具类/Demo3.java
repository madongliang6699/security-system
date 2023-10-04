package com.security.study.并发编程._14_JUC中的LockSupport工具类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            System.out.println(Thread.currentThread().getName() + ",park()之前中断标志：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + ",park()之后中断标志：" + Thread.currentThread().isInterrupted());
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
        });
        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        t1.interrupt();

        /**
         * 1696327233595,t1 start!
         * t1,park()之前中断标志：false
         * t1,park()之后中断标志：true
         * 1696327238602,t1 被唤醒!
         *
         *
         * t1线程中调用了park()方法让线程等待，主线程休眠了5秒之后，调用t1.interrupt();
         * 给线程t1发送中断信号，然后线程t1从等待中被唤醒了，输出结果中的1、4行结果相差5秒左右，
         * 刚好是主线程休眠了5秒之后将t1唤醒了。
         * 结论：park方法可以响应线程中断。
         *
         * LockSupport.park方法让线程等待之后，唤醒方式有2种：
         * 1、调用LockSupport.unpark()方法
         * 2、调用等待线程的interrupt()方法，给等待的线程发送中断信号，可以唤醒线程
         *
         * */
    }

}
