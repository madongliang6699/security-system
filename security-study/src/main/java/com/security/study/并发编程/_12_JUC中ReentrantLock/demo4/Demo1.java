package com.security.study.并发编程._12_JUC中ReentrantLock.demo4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    private static ReentrantLock lock1 = new ReentrantLock(false);
    private static ReentrantLock lock2 = new ReentrantLock(false);


    public static class T1 extends Thread {
        int numTag;
        public T1(String name, int lock) {
            super(name);
            this.numTag = lock;
        }
        @Override
        public void run() {
            try {
                if (this.numTag == 1) {
                    lock1.lockInterruptibly();
                    System.out.println(getName() + "获取了lock1");
                    TimeUnit.SECONDS.sleep(1);//睡眠1秒，以便其他线程可以及时先获取lock2
                    System.out.println(getName() + "正在获取lock2");
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    System.out.println(getName() + "获取了lock2");
                    TimeUnit.SECONDS.sleep(1);//睡眠1秒，以便其他线程可以及时先获取lock1
                    System.out.println(getName() + "正在获取lock1");
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                System.out.println("中断标志:" + this.isInterrupted());
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        /**
         *
         */

        Thread t1 = new T1("t1",1);
        Thread t2 = new T1("t2",2);

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();//这个方法是线程自带的方法，ReentrantLock里面肯定是利用了这个方法的机制。

        /**
         * t2获取了lock2
         * t1获取了lock1
         * t2正在获取lock1
         * t1正在获取lock2
         */
        /**
         * 先运行一下上面代码，发现程序无法结束，使用jstack查看线程堆栈信息，发现2个线程死锁了。
         *
         * lock1被线程t1占用，lock2被线程t2占用，线程t1在等待获取lock2，线程t2在等待获取lock1，都在相互等待获取对方持有的锁，最终产生了死锁，
         * 如果是使用synchronized关键字的方式实现上面的逻辑，程序是无法自己结束死锁的困局的。
         *
         * 但是ReentrantLock是可以通过改造代码，实现程序自动跳出死锁困局的。
         * 我们对上面代码改造一下，线程t2一直无法获取到lock1，那么等待5秒之后，我们中断获取锁的操作。主要修改一下main方法，如下：
         * 加两行代码：
         * TimeUnit.SECONDS.sleep(5);
         * t2.interrupt();
         *
         * t2在32行（lock1.lockInterruptibly();）一直获取不到lock1的锁，主线程中等待了5秒之后，t2线程调用了interrupt()方法，将线程的中断标志置为true，
         * 此时32行会触发InterruptedException异常，然后线程t2可以继续向下执行（线程都中断了为什么还向下执行，这里的中断是怎么个中断法），释放了lock2的锁，
         * 然后线程t1可以正常获取锁，程序得以继续进行。线程发送中断信号触发InterruptedException异常之后，中断标志将被清空。
         *
         *
         * 关于获取锁的过程中被中断，注意几点:
         * 1、ReentrankLock中必须使用实例方法lockInterruptibly()获取锁时，在线程调用interrupt()方法之后，才会引发InterruptedException异常
         * 2、线程调用interrupt()之后，线程的中断标志会被置为true
         * 3、触发InterruptedException异常之后，线程的中断标志会被清空，即置为false
         * 4、所以当线程调用interrupt()引发InterruptedException异常，中断标志的变化是:false->true->false
         */

    }
}
