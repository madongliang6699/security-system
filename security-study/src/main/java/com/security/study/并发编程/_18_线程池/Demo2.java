package com.security.study.并发编程._18_线程池;

import java.util.concurrent.*;

public class Demo2 {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
                //模拟任务内部处理耗时
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "处理结束" + taskName);
            });
        }
        executor.shutdown();
    }


    /**
     * 代码中使用Executors.newCachedThreadPool()创建线程池，看一下的源码：
     *
     * public static ExecutorService newCachedThreadPool() {
     *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     *     }
     *
     *
     *从输出中可以看出，系统创建了50个线程处理任务，代码中使用了SynchronousQueue同步队列，这种队列比较特殊，
     * 放入元素必须要有另外一个线程去获取这个元素【上面代码循环体中：主线程每次执行execute(),就是先往队列中放任务SynchronousQueue.offer(Runnable task)，
     * 这个动作就会阻塞，这是线程池中就要有空闲的线程来取任务SynchronousQueue.poll(keepAliveTime,TimeUnit.NANOSECONDS)，
     * 存和取配对成功，就顺利的执行了这个任务，当然，如果线程池中如果没有空闲的线程就会创建新线程取取任务，
     * 这就是为什么这个线程池要不断的创建线程的原因】，否则放入元素会失败或者一直阻塞在那里直到有线程取走，
     * 示例中任务处理休眠了指定的时间，导致已创建的工作线程都忙于处理任务，所以新来任务之后，
     * 将任务丢入同步队列会失败，丢入队列失败之后，会尝试新建线程处理任务。使用上面的方式创建线程池需要注意，
     * 如果需要处理的任务比较耗时，会导致新来的任务都会创建新的线程进行处理，可能会导致创建非常多的线程，
     * 最终耗尽系统资源，触发OOM。
     *
     */





}
