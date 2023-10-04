package com.security.study.并发编程._15_JUC中的Semaphore信号量;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo3_1 {

    static Semaphore semaphore = new Semaphore(1);//一共就一个许可
    public static class T extends Thread {
        public T(String name) {
            super(name);
        }
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            try {
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可,当前可用许可数量:" + semaphore.availablePermits());
                //休眠100秒
                TimeUnit.SECONDS.sleep(100);//让第一个线程持有这个许可100秒，100秒内其他线程只能阻塞等待
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",运行结束!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",当前可用许可数量:" + semaphore.availablePermits());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        t1.start();//这个时候第一个线程拿到了唯一的一个许可，但是要持有100秒
        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        T t2 = new T("t2");
        t2.start();
        //休眠1秒
        TimeUnit.SECONDS.sleep(1);
        T t3 = new T("t3");
        t3.start();
        //给t2和t3发送中断信号
        t2.interrupt();
        t3.interrupt();
    }


    /**
     * 程序中信号量许可数量为1，创建了3个线程获取许可，线程t1获取成功了，然后休眠100秒。
     * 其他两个线程阻塞在semaphore.acquire();方法处，代码中对线程t2、t3发送中断信号，我们看一下Semaphore中acquire的源码：
     * public void acquire() throws InterruptedException
     * 这个方法会响应线程中断，主线程中对t2、t3发送中断信号之后，acquire()方法会触发InterruptedException异常，
     * t2、t3最终没有获取到许可，但是他们都执行了finally中的释放许可的操作，最后导致许可数量变为了2，导致许可数量增加了。
     * 所以程序中释放许可的方式有问题。需要改进一下，获取许可成功才去释放锁。
     */

}
