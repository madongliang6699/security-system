package com.security.study.并发编程._20_JUC中的Executor框架详解2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * 执行一批任务，然后消费执行结果，哪个任务先执行完就先消费哪个任务的执行结果。
 */
public class CompletionServiceUtil {

    //并行线程数
    public static final int POOL_SIZE;

    static {
        //Runtime.getRuntime().availableProcessors() 获取当前系统可用的处理器数量。请注意，availableProcessors() 方法返回的是逻辑处理器的数量，而不是物理处理器的数量。在某些情况下，逻辑处理器的数量可能大于物理处理器的数量，例如在使用超线程技术的系统中。
        //max(int a, int b)方法返回用户指定的两个参数之中的较大的那一个。
        //也就是说，这里控制了并发线程数最小是5
        POOL_SIZE = Integer.max(Runtime.getRuntime().availableProcessors(), 5);
    }

    public static <T> void solve(Collection<Callable<T>> solvers, Consumer<T> use) throws InterruptedException, ExecutionException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<T> completionService = new ExecutorCompletionService<T>(executorService);

        for (Callable<T> s : solvers) {
            completionService.submit(s);
        }

        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            T r = completionService.take().get();
            if (r != null) {
                use.accept(r);
            }
        }

        executorService.shutdown();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                return j;
            });
        }
        solve(list, a -> {
            System.out.println(System.currentTimeMillis() + ":" + a);
        });
    }

    /**
     * 1696574855303:2
     * 1696574857302:4
     * 1696574859296:6
     * 1696574861302:8
     * 1696574863301:10
     *
     * 代码中传入了一批任务进行处理，最终将所有处理完成的按任务完成的先后顺序传递给Consumer进行消费了。
     */


}
