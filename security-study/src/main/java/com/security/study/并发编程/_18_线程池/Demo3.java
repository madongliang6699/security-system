package com.security.study.并发编程._18_线程池;

import java.util.concurrent.*;

public class Demo3 {

    /**
     * 看这样子应该是runnable的实体类同时实现Comparable接口compareTo方法，使其具备比较大小的功能，
     * 就可以利用PriorityBlockingQueue（优先级队列）内部具有的排序的功能。进而实现任务优先级执行。
     */
    static class Task implements Runnable, Comparable<Task> {
        private int i;
        private String name;
        public Task(int i, String name) {
            this.i = i;
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
        }
        @Override
        public int compareTo(Task o) {
            /**
             *  通过这里可以控制优先级的规则
             */
            return Integer.compare(o.i, this.i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(1, 1,
                60L, TimeUnit.SECONDS,
                new PriorityBlockingQueue());
        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(new Task(i, taskName));
        }
        for (int i = 100; i >= 90; i--) {
            String taskName = "任务" + i;
            executor.execute(new Task(i, taskName));
        }
        System.out.println("任务投入完毕======");
        executor.shutdown();
    }


    /**
     *输出中，除了第一个任务，其他任务按照优先级高低按顺序处理。
     * 原因在于：创建线程池的时候使用了优先级队列，进入队列中的任务会进行排序，任务的先后顺序由Task中的i变量决定。
     * 向PriorityBlockingQueue加入元素的时候，内部会调用代码中Task的compareTo方法决定元素的先后顺序。
     *
     */





}
