package com.security.study.并发编程._16_JUC中的CountDownLatch;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 并行处理任务的工具类,并且阻塞等待所有任务执行完毕之后再继续执行主线程
 * 注意：这里没研究如果如果其中一个任务有异常会导致什么结果，会不会所有任务都不再执行了。可以测试一下。
 * 并且这是一个没有返回执行结果的异步任务，下面的知识点中有可以返回执行结果的工具类，比如CompletionServiceUtil类
 */
public class TaskDisposeUtils {
    //并行线程数
    public static final int POOL_SIZE;

    static {
        //Runtime.getRuntime().availableProcessors() 获取当前系统可用的处理器数量。请注意，availableProcessors() 方法返回的是逻辑处理器的数量，而不是物理处理器的数量。在某些情况下，逻辑处理器的数量可能大于物理处理器的数量，例如在使用超线程技术的系统中。
        //max(int a, int b)方法返回用户指定的两个参数之中的较大的那一个。
        //也就是说，这里控制了并发线程数最小是5
        POOL_SIZE = Integer.max(Runtime.getRuntime().availableProcessors(), 5);
    }

    /**
     * 并行处理，并等待结束
     *
     * @param taskList 任务列表
     * @param consumer 消费者
     * @param <T>
     * @throws InterruptedException
     */
    public static <T> void dispose(List<T> taskList, Consumer<T> consumer) throws InterruptedException {
        dispose(true, POOL_SIZE, taskList, consumer);
    }

    /**
     * 并行处理，并等待结束
     *
     * @param moreThread 是否多线程执行
     * @param poolSize   线程池大小
     * @param taskList   任务列表
     * @param consumer   消费者（处理任务的逻辑）
     * @param <T>
     * @throws InterruptedException
     */
    public static <T> void dispose(boolean moreThread, int poolSize, List<T> taskList, Consumer<T> consumer) throws InterruptedException {
        if (CollectionUtils.isEmpty(taskList)) {
            return;
        }
        if (moreThread && poolSize > 1) {
            poolSize = Math.min(poolSize, taskList.size());
            ExecutorService executorService = null;
            try {
                executorService = Executors.newFixedThreadPool(poolSize);
                CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
                for (T item : taskList) {
                    System.out.println(JSONObject.toJSONString(item) + "任务已经开始执行");
                    //给每个任务启动一个线程执行
                    executorService.execute(() -> {
                        try {
                            consumer.accept(item);
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                }
                //阻塞，等待所有线程（所有任务）都执行完毕，再放行退出
                countDownLatch.await();
            } finally {
                if (executorService != null) {
                    executorService.shutdown();
                }
            }
        } else {
            //串行处理任务
            for (T item : taskList) {
                consumer.accept(item);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //生成1-10的10个数字，放在list中，相当于10个任务
//        List<Integer> list = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());
        List<Integer> list = Arrays.asList(3,4,9,3,5,12,23);
        //启动多线程处理list中的数据，每个任务休眠时间为list中的数值
        TaskDisposeUtils.dispose(list, new Consumer<Integer>() {
            @Override
            public void accept(Integer item) {
                try {
                    long startTime = System.currentTimeMillis();
                    TimeUnit.SECONDS.sleep(item);
                    long endTime = System.currentTimeMillis();
                    System.out.println(System.currentTimeMillis() + ",任务" + item + "执行完毕，耗时:" + (endTime - startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //上面所有任务处理完毕完毕之后，程序才能继续
        System.out.println(list + "中的任务都处理完毕!");
    }
}