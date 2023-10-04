package com.security.study.并发编程._13_JUC中的Condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();//Condition由ReentrantLock创建


    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start");
                boolean r = condition.await(2, TimeUnit.SECONDS);
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

        /**
         * 1696322330100,t1,start
         * false
         * 1696322332106,t1,endse
         *
         *t1线程等待2秒之后，自动返回继续执行，最后await方法返回false，await返回false表示超时之后自动返回
         */
    }


}
