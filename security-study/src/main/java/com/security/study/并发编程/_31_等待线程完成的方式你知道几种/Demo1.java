package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.TimeUnit;

public class Demo1 {

    //用于封装结果
    static class Result<T> {
        T result;
        public T getResult() {
            return result;
        }
        public void setResult(T result) {
            this.result = result;
        }
    }



    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        //用于存放子线程执行的结果
        Result<Integer> result = new Result<>();
        //创建一个子线程
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                result.setResult(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //让主线程等待thread线程执行完毕之后再继续，join方法会让当前线程阻塞
        thread.join();
        //获取thread线程的执行结果
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);

        /**
         * 代码中通过join方式阻塞了当前主线程，当thread线程执行完毕之后，join方法才会继续执行。
         *
         *  join方法的底层原理:
         * 底层实现是基于对象的wait和notify方法来实现的。当一个线程A执行另一个线程B的join()方法时，因
         * 为join方法是synchronized关键字修饰的，所以线程A会拿到线程B的对象锁，然后在底层会执行线程
         * B的wait方法，那么线程A就会释放锁并进入线程B的等待池中，当线程B执行完毕后，JVM底层会自
         * 动调用线程B对象的notifyAll()方法来通知所有等待该对象锁上的线程，包括线程A。这个时候线程A
         * 会重新进入就绪状态，等待获取CPU资源继续向下执行.
         *
         * 注意点一：join阻塞的是当前线程，并不是join方法的线程对象对应的线程
         * 有很多人不理解join为什么阻塞的是当前线程，而不是调用join方法的线程对象对应的线程呢？不理
         * 解的原因是阻塞当前线程A的wait()方法是放在线程对象B这个实例中被调用的，让大家误以为应该阻
         * 塞B线程。但实际上当前线程会持有线程对象B的对象的锁（因为join方法使用synchronized修饰的，
         * 所以当前线程也就获取了线程对象B的锁），在线程对象B调用的wait()方法时，而这个wait()方法的
         * 调用者线程对象B是在当前线程环境中的。所以造成当前线程阻塞。
         *
         * 注意点二：唤醒当前线程的操作是在JVM底层实现的，并没有显式调用
         * notifyAll()方法
         * 为什么线程B执行完毕就能够唤醒当前线程呢？或者说是在什么时候唤醒的？
         * 这里我们要注意一点，被等待的线程并不会真正地调用notifyAll()方法来唤醒其他等待线程，而是由
         * 底层的JVM代码实现自动唤醒等待线程的功能。这个功能在底层被称为“monitor enter”和“monitor
         * exit”，是由JVM来负责管理的。具体实现细节比较复杂，但是对于Java开发者来说，只需要知道在
         * 使用join()方法等待线程执行完毕时，等待的线程会被自动唤醒，不需要手动调用notify()或notifyAll()
         * 方法。在Thread类的join()方法的源码中，没有直接调用notify()或notifyAll()方法的代码。但是，join()
         * 方法的底层实现确实是基于对象的wait()和notify()方法来实现的。
         * 如果想要知道实现唤醒的具体细节，我们就得翻jdk的源码，但是如果大家对线程有一定的基本了解
         * 的话，通过wait方法阻塞的线程，需要通过notify()或者notifyAll()来唤醒。所以在线程执行完毕以后会有
         * 一个唤醒的操作，只不过并不是显式调用，而是在JVM底层代码实现的。
         */
    }
}
