package com.security.study.并发编程._20_JUC中的Executor框架详解2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            String taskName = "任务" + i;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                System.out.println(taskName + "执行完毕!");
                return j;
            });
        }
        Integer integer = executorService.invokeAny(list);
        System.out.println("耗时:" + (System.currentTimeMillis() - startime) + ",执行结果:" + integer);
        executorService.shutdown();
    }

    /**
     * 任务1执行完毕!
     * 耗时:2054,执行结果:2
     *
     * 输出结果和方式1中结果类似。
     */
}