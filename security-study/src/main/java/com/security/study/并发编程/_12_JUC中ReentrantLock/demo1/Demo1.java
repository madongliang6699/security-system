package com.security.study.并发编程._12_JUC中ReentrantLock.demo1;

public class Demo1 {

    private static Integer num = 0;

    private static synchronized void aa(){
        num++;
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
         * synchronized的方式实现三个线程累加num变量。
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
