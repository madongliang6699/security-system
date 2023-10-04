package com.security.study.并发编程._14_JUC中的LockSupport工具类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
        });
        t1.setName("t1");
        t1.start();

        //休眠5秒
        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(t1);
        System.out.println(System.currentTimeMillis() + ",LockSupport.unpark();执行完毕");

        /**
         * 1696327061290,LockSupport.unpark();执行完毕
         * 1696327064299,t1 start!
         * 1696327064299,t1 被唤醒!
         *
         *
         * 代码中启动t1线程，t1线程内部休眠了5秒，然后主线程休眠1秒之后，调用了LockSupport.unpark(t1);唤醒线程t1，
         * 此时LockSupport.park();方法还未执行，说明唤醒方法在等待方法之前执行的；
         * 输出结果中2、3行结果时间一样，表示LockSupport.park();没有阻塞了，是立即返回的。
         *
         * 说明：唤醒方法在等待方法之前执行，线程也能够被唤醒，这点是另外2中方法无法做到的。
         * Object和Condition中的唤醒必须在等待之后调用，线程才能被唤醒。
         * 而LockSupport中，唤醒的方法不管是在等待之前还是在等待之后调用，线程都能够被唤醒。
         *
         * */
    }

}
