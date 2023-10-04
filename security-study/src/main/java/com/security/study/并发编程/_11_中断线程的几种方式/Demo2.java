package com.security.study.并发编程._11_中断线程的几种方式;

import java.util.concurrent.TimeUnit;

public class Demo2 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                if (this.isInterrupted()) {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();

        /**
         * 运行上面的程序，程序可以正常结束。线程内部有个中断标志，当调用线程的interrupt()实例方法之后，
         * 线程的中断标志会被置为true，可以通过线程的实例方法isInterrupted()获取线程的中断标志。
         */
    }
}
