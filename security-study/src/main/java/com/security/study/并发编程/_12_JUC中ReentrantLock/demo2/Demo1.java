package com.security.study.并发编程._12_JUC中ReentrantLock.demo2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    private static Integer num = 0;

    private static final Lock lock = new ReentrantLock();


    private static void aa(){
        lock.lock();
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
//            lock.unlock();
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                Demo1.aa();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * ReentrantLock方式：实现三个线程累加num变量。
         *
         * 来验证一下ReentrantLock是可重入锁：
         * 上面代码中add()方法中，当一个线程进入的时候，会执行2次获取锁的操作，运行程序可以正常结束，并输出和期望值一样的30000，
         * 假如ReentrantLock是不可重入的锁，那么同一个线程第2次获取锁的时候由于前面的锁还未释放而导致死锁，程序是无法正常结束的。
         * ReentrantLock命名也挺好的Re entrant Lock，和其名字一样，可重入锁。
         *
         * 代码中还有几点需要注意：
         * 1、lock()方法和unlock()方法需要成对出现，锁了几次，也要释放几次，否则后面的线程无法获取锁了；
         * 可以将add中的unlock()注释掉一个试试，上面代码运行将无法结束
         * 2、unlock()方法放在finally中执行，保证不管程序是否有异常，锁必定会释放
         *
         */

        Thread t1 = new T1();
        Thread t2 = new T1();
        Thread t3 = new T1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();


        System.out.println("num=" + num);

    }
}
