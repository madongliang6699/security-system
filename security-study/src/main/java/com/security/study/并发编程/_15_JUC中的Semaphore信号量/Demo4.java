package com.security.study.并发编程._15_JUC中的Semaphore信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo4 {


    static Semaphore semaphore = new Semaphore(1);
    public static class T extends Thread {
        public T(String name) {
            super(name);
        }
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            //获取许可是否成功
            boolean acquireSuccess = false;
            try {
                //尝试在1秒内获取许可，获取成功返回true，否则返回false
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",尝试获取许可,当前可用许可数量:" + semaphore.availablePermits());
                acquireSuccess = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                //获取成功执行业务代码
                if (acquireSuccess) {
                    System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可成功,当前可用许可数量:" + semaphore.availablePermits());
                    //休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可失败,当前可用许可数量:" + semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (acquireSuccess) {
                    semaphore.release();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        t1.start();
        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        T t2 = new T("t2");
        t2.start();
        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        T t3 = new T("t3");
        t3.start();
    }

    /**
     * 1696415075282,t1,尝试获取许可,当前可用许可数量:1
     * 1696415075282,t1,获取许可成功,当前可用许可数量:0
     * 1696415076298,t2,尝试获取许可,当前可用许可数量:0
     * 1696415077304,t2,获取许可失败,当前可用许可数量:0
     * 1696415077304,t3,尝试获取许可,当前可用许可数量:0
     * 1696415078308,t3,获取许可失败,当前可用许可数量:0
     *
     * 代码中许可数量为1，semaphore.tryAcquire(1, TimeUnit.SECONDS);：
     * 表示尝试在1秒内获取许可，获取成功立即返回true，超过1秒还是获取不到，返回false。
     * 线程t1获取许可成功，之后休眠了5秒，从输出中可以看出t2和t3都尝试了1秒，获取失败。
     *
     * 【并且这个semaphore.tryAcquire(1, TimeUnit.SECONDS);方法本身就
     * 返回了一个是否获取锁成功的变量，finally里面可以根据这个变量判断是否要释放一下锁，就不用我们自己双肩一个变量标记了。
     * 自动就是正确的释放锁的姿势了】
     */

}
