package com.security.study.并发编程._11_中断线程的几种方式;

import java.util.concurrent.TimeUnit;

public class Demo1 {


    public volatile static boolean exit = false;//必须volatile修饰，保证可见性
    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                if (exit) {
                    break;
                }
            }
        }
    }
    public static void setExit() {
        exit = true;
    }
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        setExit();

        /**
         * 代码中启动了一个线程，线程的run方法中有个死循环，内部通过exit变量的值来控制是否退出。
         * 程序有个重点：volatile关键字，exit变量必须通过这个修饰，如果把这个去掉，程序无法正常退出。
         * volatile控制了变量在多线程中的可见性，关于volatile前面的文章中有介绍，此处就不再说了。
         */
    }
}
