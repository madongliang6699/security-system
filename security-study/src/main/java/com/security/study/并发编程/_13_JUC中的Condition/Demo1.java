package com.security.study.并发编程._13_JUC中的Condition;

import java.util.concurrent.TimeUnit;

public class Demo1 {


    static Object lock = new Object();
    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                try {
                    lock.wait();//当前线程等待，并立即释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }
    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                lock.notify();
                System.out.println(System.currentTimeMillis() + "," + this.getName() + " notify!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
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
         * 1696316568413,t1准备获取锁!
         * 1696316568413,t1获取锁成功!
         * 1696316573421,t2准备获取锁!
         * 1696316573421,t2获取锁成功!
         * 1696316573422,t2 notify!
         * 1696316578437,t2准备释放锁!
         * 1696316578438,t1释放锁成功!
         * 1696316578439,t2释放锁成功!
         *
         * 代码结合输出的结果我们分析一下：
         * 1、线程t1先获取锁，然后调用了wait()方法将线程置为等待状态，然后会释放lock的锁
         * 2、主线程等待5秒之后，启动线程t2，t2获取到了锁，结果中1、3行时间相差5秒左右
         * 3、t2调用lock.notify()方法，准备将等待在lock上的线程t1唤醒，notify()方法之后又休眠了5秒，看一下输出的5、8可知，notify()方法之后，t1并不能立即被唤醒，需要等到t2将synchronized块执行完毕，释放锁之后，t1才被唤醒
         * 4、wait()方法和notify()方法必须放在同步块内调用（synchronized块内），否则会报错
         */
    }

}
