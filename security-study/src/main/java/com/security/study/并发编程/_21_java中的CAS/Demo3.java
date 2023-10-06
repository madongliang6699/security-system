package com.security.study.并发编程._21_java中的CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo3 {
    //访问次数
    volatile static int count = 0;

    //模拟访问一次
    public static void request() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount;
        do {
            expectCount = getCount();
        } while (!compareAndSwap(expectCount, expectCount + 1));
    }

    /**
     * 获取count当前的值
     *
     * @return
     */
    public static int getCount() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);//如果这里比较耗时，通过compareAndSwap(int expectCount, int newCount)方法内的打印可以看出，会有很多比较失败的次数，并且是多个线程为了改变同一个数值而不断的相互
        return count;
    }

    /**
     * @param expectCount 期望count的值
     * @param newCount    需要给count赋的新值
     * @return
     */
    public static synchronized boolean compareAndSwap(int expectCount, int newCount) throws InterruptedException {
        //判断count当前值是否和期望的expectCount一样，如果一样将newCount赋值给count
        if (getCount() == expectCount) {
            count = newCount;
            System.out.println(Thread.currentThread().getName()+ "比较成功：expectCount：" + expectCount + "，newCount:" + newCount + "，count:" + count);
            return true;
        }
        System.out.println(Thread.currentThread().getName()+ "比较失败了--：expectCount：" + expectCount + "，newCount:" + newCount + "，count:" + count);

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
    }

    /**
     * main，耗时：116,count=1000
     *
     *
     *代码中用了volatile关键字修饰了count，可以保证count在多线程情况下的可见性。关于volatile关键字的使用，也是非常非常重要的，前面有讲过，不太了解的朋友可以去看一下：volatile与Java内存模型
     *
     * 咱们再看一下代码，compareAndSwap方法，我们给起个简称吧叫CAS，这个方法有什么作用呢？这个方法使用synchronized修饰了，能保证此方法是线程安全的，多线程情况下此方法是串行执行的。方法由两个参数，expectCount：表示期望的值，newCount：表示要给count设置的新值。方法内部通过getCount()获取count当前的值，然后与期望的值expectCount比较，如果期望的值和count当前的值一致，则将新值newCount赋值给count。
     *
     * 再看一下request()方法，方法中有个do-while循环，循环内部获取count当前值赋值给了expectCount，循环结束的条件是compareAndSwap返回true，也就是说如果compareAndSwap如果不成功，循环再次获取count的最新值，然后+1，再次调用compareAndSwap方法，直到compareAndSwap返回成功为止。
     *
     * 代码中相当于将count++拆分开了，只对最后一步加锁了，减少了锁的范围，此代码的性能是不是比方式2快不少，还能保证结果的正确性。大家是不是感觉这个compareAndSwap方法挺好的，这东西确实很好，java中已经给我们提供了CAS的操作，功能非常强大，我们继续向下看。
     *
     *
     */

}
