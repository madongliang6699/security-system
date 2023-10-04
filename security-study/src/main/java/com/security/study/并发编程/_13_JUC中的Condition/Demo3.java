package com.security.study.并发编程._13_JUC中的Condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();//Condition由ReentrantLock创建


    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
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
//        TimeUnit.SECONDS.sleep(2);

        //给t1线程发送中断信号
        System.out.println("1、t1中断标志：" + t1.isInterrupted());
        t1.interrupt();
        System.out.println("2、t1中断标志：" + t1.isInterrupted());


        /**
         * 1、t1中断标志：false
         * 2、t1中断标志：true
         * 中断标志：false
         *
         *调用condition.await()之后，线程进入阻塞中，调用t1.interrupt()，给t1线程发送中断信号，
         * await()方法内部会检测到线程中断信号，然后触发InterruptedException异常，线程中断标志被清除。
         * 从输出结果中可以看出，线程t1中断标志的变换过程：false->true->false
         */
    }


}
