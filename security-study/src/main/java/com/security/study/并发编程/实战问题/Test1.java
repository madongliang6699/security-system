package com.security.study.并发编程.实战问题;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Test1 {


    public static void main(String[] args) throws Exception {

        /**
         * 该案例的坑是在中智达优化风险业务接口的时候发现的，这里用查询订单模拟复现这个坑。
         *
         * 查订单业务场景说明：
         * 在本服务（订单服务）中已经查询出30条订单，但是订单条目数据和物流数据需要到其他服务查询数据填充但订单信息中。
         * 因此,这里模拟遍历30条订单，每个订单异步的去其他服务取自己的条目和物流数据。
         *
         */

        // 模拟已经在本服务查出了30条订单基础数据
        List<MyOrderVO> orderVOList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            MyOrderVO vo = new MyOrderVO();
            vo.setOrderId(i);
            vo.setOrderName("name" + 1);
            vo.setOrderAddr("杭州");
            orderVOList.add(vo);
        }


        //线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                15,
                50,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        ThreadPoolExecutor poolExecutor2 = new ThreadPoolExecutor(
                15,
                50,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );


        CountDownLatch countDownLatchWaiCeng = new CountDownLatch(orderVOList.size());
        //一次性有30个任务要执行，
        orderVOList.forEach(orderVO -> {
            poolExecutor.execute(() -> {
                //这里去根据订单id，rpc调用取条目和物流数据，因为查条目和查物流是不互相关联的，因此这里也可以异步处理。就继续使用线程池异步处理取数据这两步
                Integer orderId = orderVO.getOrderId();

                CountDownLatch countDownLatchNeiBceng = new CountDownLatch(2);
                poolExecutor.execute(() -> {
                    try {
                        List<String> strings = selectOrderItemByOrderId(orderId);
                        orderVO.setOrderItem(strings);//填充数据
                    } catch (InterruptedException e) {
                    }
                    System.out.println("--"+Thread.currentThread().getName());
                    countDownLatchNeiBceng.countDown();
                });
                poolExecutor.execute(() -> {
                    try {
                        String wuliu = selectWuLiuByOrderId(orderId);
                        orderVO.setOrderWuLiu(wuliu);//填充数据
                    } catch (InterruptedException e) {
                    }
                    System.out.println("---"+Thread.currentThread().getName());
                    countDownLatchNeiBceng.countDown();
                });

                try {
                    //当前订单的任务，必须确保条目和物流数据已经填充好了 才能算执行完毕，所以这里必须等待两个内层的异步任务执行完毕。
                    countDownLatchNeiBceng.await();
                } catch (InterruptedException e) {
                }
                System.out.println("---"+Thread.currentThread().getName());
                countDownLatchWaiCeng.countDown();
            });
        });

//        TimeUnit.SECONDS.sleep(3);
        //等上面的异步执行完，返回组装好的 orderVOList 数据。
        countDownLatchWaiCeng.await();
        System.out.println("-----"+orderVOList);
        poolExecutor.shutdown();
        poolExecutor2.shutdown();

        /**
         * 总结：这个案例只要一执行，几乎每次都会被卡住执行不下去。
         * 原因：
         * 因为同一个线程池执行了内外两层任务，同一个订单的外层任务必须等待内层的两个异步任务执行完毕执行才能继续执行完毕。
         * 当然内层的任务不需要等待任何其他任务，每个订单的内存执行完毕就继续执行完外层任务。每个订单的任务互不相干。
         * 所以看起来这块代码似乎没什么问题，并且充分使用了异步，做到了性能优化，很完美。
         *
         * 但是，因为内外层异步任务使用了同一个线程池，并且线程池的核心大小只有15，而订单外层任务就有30条，导致代码一运行，首先外层的
         * 30个任务可能就一下子把核心线程数的15个线程给用完了，其他后面的任务都放到的队列里，其中包括内层任务，
         * 要命的就是这里，所有订单的内层两个异步任务都被放到队列里等待执行，而已经在执行的15个外层任务又都因为countDownLatchNeiBceng在等待
         * 内层的两个任务执行完毕。导致内外层任务互相等待，死锁，一种特殊的死锁。
         *
         * 造成上面死锁的关键点：
         * 1、核心线程的数量小于任务的数据（并且小于外层任务的数量）。如果核心线程数量大于外层任务数量，就不会造成所有内部任务都被放进队列等待。
         * 2、同一个线程池需要执行内外两层任务，而外层任务又必须等内存任务执行完毕。如果外层不必等待内层，或者内外层的任务使用不同的线程池，就没问题了。
         * 3、或者队列容量足够小也行，对象容量小放不下任务，就会触发最大线程数量新建线程取执行任务，这样也可以执行到内部任务，进而完成任务。
         *    但是如果最大线程数比任务数小很多的话，会触发拒绝策略，导致报错或任务被抛弃，进而数据填充不完成。
         *
         * 建议：
         * 综上，同一个线程池还是不要写有内外嵌套异步的代码。要不然很容易出问题。
         */
    }


    public static List<String> selectOrderItemByOrderId(Integer orderId) throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(300);
        return Arrays.asList("条目1","条目2","条目3");
    }

    public static String selectWuLiuByOrderId(Integer orderId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        return "物流信息aaaaa";
    }


    @Data
    static class MyOrderVO {
        //订单id
        private Integer orderId;
        //订单名称
        private String orderName;
        //收货地址
        private String orderAddr;
        //订单内详细商品sku条目（需要请求条目服务填充数据）
        private List<String> orderItem;
        //订单物流信息（需要请求物流服务填充数据）
        private String orderWuLiu;

    }


}
