package com.security.study.并发编程._16_JUC中的CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo4 {


    public static class T extends Thread {
        //跑步耗时（秒）
        int runCostSeconds;
        CountDownLatch commanderCd;
        CountDownLatch countDown;
        public T(String name, int runCostSeconds, CountDownLatch commanderCd, CountDownLatch countDown) {
            super(name);
            this.runCostSeconds = runCostSeconds;
            this.commanderCd = commanderCd;
            this.countDown = countDown;
        }
        @Override
        public void run() {
            //等待指令员枪响
            try {
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ",开始跑!");
            try {
                //模拟耗时操作，休眠runCostSeconds秒
                TimeUnit.SECONDS.sleep(this.runCostSeconds);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime + "," + ct.getName() + ",跑步结束,耗时:" + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDown.countDown();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start!");
        CountDownLatch commanderCd = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        T t1 = new T("小张", 2, commanderCd, countDownLatch);
        t1.start();
        T t2 = new T("小李", 5, commanderCd, countDownLatch);
        t2.start();
        T t3 = new T("路人甲", 10, commanderCd, countDownLatch);
        t3.start();
        //主线程休眠5秒,模拟指令员准备发枪耗时操作
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + ",枪响了，大家开始跑");
        commanderCd.countDown();
        long starTime = System.currentTimeMillis();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "所有人跑完了，跑步总耗时:" + (endTime - starTime));
    }

    /**
     * 1696421157575,main线程 start!
     * 1696421162593,枪响了，大家开始跑
     * 1696421162593,小张,开始跑!
     * 1696421162593,路人甲,开始跑!
     * 1696421162593,小李,开始跑!
     * 1696421164600,小张,跑步结束,耗时:2007
     * 1696421167605,小李,跑步结束,耗时:5012
     * 1696421172612,路人甲,跑步结束,耗时:10019
     * 1696421172613,main所有人跑完了，跑步总耗时:10020
     *
     *
     * 代码中，t1、t2、t3启动之后，都阻塞在commanderCd.await();，主线程模拟发枪准备操作耗时5秒，
     * 然后调用commanderCd.countDown();模拟发枪操作，此方法被调用以后，阻塞在commanderCd.await();的3个线程会向下执行。
     * 主线程调用countDownLatch.await();之后进行等待，
     * 每个人跑完之后，调用countDown.countDown();通知一下countDownLatch让计数器减1，
     * 最后3个人都跑完了，主线程从countDownLatch.await();返回继续向下执行。
     *
     * 【如果有个场景需要多个线程在某个时间点同时开始执行，就可以使用这个案例。
     * 或者说需要等待所有线程都就位之后一起执行也可以参考这个案例的思想。
     * 反正，只要是有需要等待的场景，无论是你等待我还是我又反过来等待你，都可以参考这个案例的思想，在其基础上进行改造】
     */

}
