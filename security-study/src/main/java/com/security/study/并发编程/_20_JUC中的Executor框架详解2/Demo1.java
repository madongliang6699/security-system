package com.security.study.并发编程._20_JUC中的Executor框架详解2;

import java.util.concurrent.*;

public class Demo1 {
    static class GoodsModel {
        //商品名称
        String name;
        //购物开始时间
        long startime;
        //送到的时间
        long endtime;
        public GoodsModel(String name, long startime, long endtime) {
            this.name = name;
            this.startime = startime;
            this.endtime = endtime;
        }
        @Override
        public String toString() {
            return name + "，下单时间[" + this.startime + "," + endtime + "]，耗时:" + (this.endtime - this.startime);
        }
    }
    /**
     * 将商品搬上楼
     *
     * @param goodsModel
     * @throws InterruptedException
     */
    static void moveUp(GoodsModel goodsModel) throws InterruptedException {
        //休眠5秒，模拟搬上楼耗时
        TimeUnit.SECONDS.sleep(5);
        System.out.println("将商品搬上楼，商品信息:" + goodsModel);
    }
    /**
     * 模拟下单
     *
     * @param name     商品名称
     * @param costTime 耗时
     * @return
     */
    static Callable<GoodsModel> buyGoods(String name, long costTime) {
        return () -> {
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "购买" + name + "下单!");
            //模拟送货耗时
            try {
                TimeUnit.SECONDS.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + name + "送到了!");
            return new GoodsModel(name, startTime, endTime);
        };
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long st = System.currentTimeMillis();
        System.out.println(st + "开始购物!");

        ExecutorService executor = Executors.newFixedThreadPool(5);

        //创建ExecutorCompletionService对象，传入一个用来执行任务的线程池
        ExecutorCompletionService<GoodsModel> executorCompletionService = new ExecutorCompletionService<>(executor);
        //异步下单购买冰箱
        executorCompletionService.submit(buyGoods("冰箱", 5));
        //异步下单购买洗衣机
        executorCompletionService.submit(buyGoods("洗衣机", 2));
        executor.shutdown();
        //购买商品的数量
        int goodsCount = 2;
        for (int i = 0; i < goodsCount; i++) {
            //可以获取到最先到的商品【这里就是从内部的队列中获取已经完成的任务的执行结果，队列中的任务结果是按照先后排序的】
            GoodsModel goodsModel = executorCompletionService.take().get();
            //将最先到的商品送上楼
            moveUp(goodsModel);
        }
        long et = System.currentTimeMillis();
        System.out.println(et + "货物已送到家里咯，哈哈哈！");
        System.out.println("总耗时:" + (et - st));
    }

    /**
     * 看一下上面 ExecutorCompletionService 的构造方法：
     * public ExecutorCompletionService(Executor executor) {
     *         if (executor == null)
     *             throw new NullPointerException();
     *         this.executor = executor;
     *         this.aes = (executor instanceof AbstractExecutorService) ?
     *             (AbstractExecutorService) executor : null;
     *         this.completionQueue = new LinkedBlockingQueue<Future<V>>();
     *     }
     * 构造方法需要传入一个Executor对象，这个对象表示任务执行器，所有传入的任务会被这个执行器执行。
     * completionQueue是用来存储任务结果的阻塞队列，默认用采用的是LinkedBlockingQueue，也支持开发自己设置。
     * 通过submit传入需要执行的任务，任务执行完成之后，会放入completionQueue中，有兴趣的可以看一下原码，还是很好理解的。
     *
     *
     * 1696573632196开始购物!
     * 1696573632259购买冰箱下单!
     * 1696573632259购买洗衣机下单!
     * 1696573634272洗衣机送到了!
     * 1696573637274冰箱送到了!
     * 将商品搬上楼，商品信息:洗衣机，下单时间[1696573632259,1696573634272]，耗时:2013
     * 将商品搬上楼，商品信息:冰箱，下单时间[1696573632259,1696573637274]，耗时:5015
     * 1696573644278货物已送到家里咯，哈哈哈！
     * 总耗时:12082
     *
     * 从输出中可以看出和我们希望的结果一致，代码中下单顺序是：冰箱先下单、洗衣机后下单，冰箱送货耗时5秒，洗衣机送货耗时2秒，洗衣机却是先到的，
     * 然后就先送洗衣机上楼了，冰箱后到被送上楼，总共耗时12秒，和期望的方案一样。
     *
     * 【这个功能应该在工作业务中最常使用把，异步执行多个任务，哪个先执行完就先处理哪个】
     */
}