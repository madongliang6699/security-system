package com.security.study.并发编程._12_JUC中ReentrantLock.demo3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {


    /**
     * 这里设置为true是公平锁，从输出的结果可以看到，三个线程获取锁的顺序几乎就是轮流的，但是设置为false的话，就同一个线程连续一直能获取到锁。
     */
    private static final Lock fairLock = new ReentrantLock(true);


    public static class T1 extends Thread {

        public T1(String name){
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                fairLock.lock();
                try {
                    System.out.println(this.getName() + "获得锁!");
                } finally {
                    fairLock.unlock();
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
        Thread t3 = new T1("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();


    }
}
