package com.security.study.并发编程._13_JUC中的Condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();//Condition由ReentrantLock创建


    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start");
                boolean r = condition.await(5, TimeUnit.SECONDS);
                System.out.println(r);
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",end");
            } catch (InterruptedException e) {
                System.out.println("中断标志：" + this.isInterrupted());
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();

        //休眠1秒之后，唤醒t1线程
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }

        /**
         * 1696322454539,t1,start
         * true
         * 1696322455553,t1,end
         *
         * t1线程中调用condition.await(5, TimeUnit.SECONDS);方法会释放锁，等待5秒，主线程休眠1秒，然后获取锁，
         * 之后调用signal()方法唤醒t1，输出结果中发现await后过了1秒（1、3行输出结果的时间差），await方法就返回了，
         * 并且返回值是true。true表示await方法超时之前被其他线程唤醒了。
         */
    }


}
