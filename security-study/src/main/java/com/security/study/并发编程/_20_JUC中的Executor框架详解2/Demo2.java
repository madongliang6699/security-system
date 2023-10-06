package com.security.study.并发编程._20_JUC中的Executor框架详解2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Demo2 {

    public static <T> T invokeAny(Executor e, Collection<Callable<T>> solvers) throws InterruptedException, ExecutionException {
        CompletionService<T> ecs = new ExecutorCompletionService<T>(e);
        List<Future<T>> futureList = new ArrayList<>();
        for (Callable<T> s : solvers) {
            futureList.add(ecs.submit(s));
        }
        int n = solvers.size();
        try {
            for (int i = 0; i < n; ++i) {
                T r = ecs.take().get();
                if (r != null) {
                    return r;
                }
            }
        } finally {
            for (Future<T> future : futureList) {
                future.cancel(true);
            }
        }
        return null;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            String taskName = "任务"+i;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                System.out.println(taskName+"执行完毕!");
                return j;
            });
        }
        Integer integer = invokeAny(executorService, list);
        System.out.println("耗时:" + (System.currentTimeMillis() - startime) + ",执行结果:" + integer);
        executorService.shutdown();
    }

    /**
     *任务1执行完毕!
     * 耗时:2054,执行结果:2
     *
     * 代码中执行了5个任务，使用CompletionService执行任务，调用take方法获取最先执行完成的任务，然后返回。
     * 在finally中对所有任务发送取消操作（future.cancel(true);），从输出中可以看出只有任务1执行成功，
     * 其他任务被成功取消了，符合预期结果。
     */
}