package com.security.study.并发编程._13_JUC中的Condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();//Condition由ReentrantLock创建


    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }
    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.signal();
                System.out.println(System.currentTimeMillis() + "," + this.getName() + " signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();

        /**
         * 1696321151969,t1准备获取锁!
         * 1696321151969,t1获取锁成功!
         * 1696321156982,t2准备获取锁!
         * 1696321156982,t2获取锁成功!
         * 1696321156982,t2 signal!
         * 1696321161983,t2准备释放锁!
         * 1696321161985,t2释放锁成功!
         * 1696321161985,t1释放锁成功!
         *输出的结果和使用synchronized关键字的实例类似。
         *
         * Condition.await()方法和Object.wait()方法类似，当使用Condition.await()方法时，
         * 需要先获取Condition对象关联的ReentrantLock的锁，在Condition.await()方法被调用时，
         * 当前线程会释放这个锁，并且当前线程会进行等待（处于阻塞状态）。
         * 在signal()方法被调用后，系统会从Condition对象的等待队列中唤醒一个线程，一旦线程被唤醒，
         * 被唤醒的线程会尝试重新获取锁，一旦获取成功，就可以继续执行了。
         * 因此，在signal被调用后，一般需要释放相关的锁（做unlock()操作），让给其他被唤醒的线程，让他可以继续执行。
         *
         */
    }


}
