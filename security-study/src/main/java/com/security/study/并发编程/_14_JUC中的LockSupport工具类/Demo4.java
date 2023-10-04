package com.security.study.并发编程._14_JUC中的LockSupport工具类;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Demo4 {

    static class BlockerDemo {

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            LockSupport.park(new BlockerDemo());
        });
        t2.setName("t2");
        t2.start();
    }
    /**
     *运行上面代码，然后用jstack查看一下线程的堆栈信息：
     *
     * "t2" #13 prio=5 os_prio=0 tid=0x00000000293ea800 nid=0x91e0 waiting on condition [0x0000000029c3f000]
     *    java.lang.Thread.State: WAITING (parking)
     *         at sun.misc.Unsafe.park(Native Method)
     *         - parking to wait for  <0x00000007180bfeb0> (a com.itsoku.chat10.Demo10$BlockerDemo)
     *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
     *         at com.itsoku.chat10.Demo10.lambda$main$1(Demo10.java:22)
     *         at com.itsoku.chat10.Demo10$$Lambda$2/824909230.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:745)
     * "t1" #12 prio=5 os_prio=0 tid=0x00000000293ea000 nid=0x9d4 waiting on condition [0x0000000029b3f000]
     *    java.lang.Thread.State: WAITING (parking)
     *         at sun.misc.Unsafe.park(Native Method)
     *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:304)
     *         at com.itsoku.chat10.Demo10.lambda$main$0(Demo10.java:16)
     *         at com.itsoku.chat10.Demo10$$Lambda$1/1389133897.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:745)
     *
     * 代码中，线程t1和t2的不同点是，t2中调用park方法传入了一个BlockerDemo对象，从上面的线程堆栈信息中，
     * 发现t2线程的堆栈信息中多了一行- parking to wait for <0x00000007180bfeb0> (a com.itsoku.chat10.Demo10$BlockerDemo)，
     * 刚好是传入的BlockerDemo对象，park传入的这个参数可以让我们在线程堆栈信息中方便排查问题，其他暂无他用。
     * */

}
