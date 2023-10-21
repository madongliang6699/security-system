package com.security.study.并发编程._27_实战_你的接口太慢了需要优化;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Demo1 {

    /**
     * 获取商品基本信息
     */
    public String goodsDetailModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品基本信息....";
    }

    /**
     * 获取商品图片列表
     */
    public List<String> goodsImgsModelList(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return Arrays.asList("图1", "图2", "图3");
    }

    /**
     * 获取商品描述信息
     */
    public String goodsExtModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品描述信息......";
    }




    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Demo1 demo1 = new Demo1();

        //如果是按顺序同步执行上面的查询过程
        long start = System.currentTimeMillis();
        Map<String, Object> dataBySync = demo1.getDataBySync();
        long end = System.currentTimeMillis();
        System.out.println("同步耗时：" + (end - start));//同步耗时：620
        System.out.println(dataBySync);

        //如果是异步执行
        start = System.currentTimeMillis();
        dataBySync = demo1.getDataByAsync();
        end = System.currentTimeMillis();
        System.out.println("异步耗时：" + (end - start));//异步耗时：253
        System.out.println(dataBySync);

        /**
         * 可以看出耗时200多毫秒左右，性能提升了2倍多，假如这个接口中还存在其他无依赖的操作，
         * 性能提升将更加显著，上面使用了线程池并行去执行3次查询的任务，最后通过Future获取异步执行结果。
         *
         * 整个优化过程：
         * 先列出无依赖的一些操作
         * 将这些操作改为并行的方式
         */

    }

    /**
     * 同步的方式查询数据
     */
    private Map<String, Object> getDataBySync() throws InterruptedException {

        String goodsDetailModel = goodsDetailModel(111);
        List<String> goodsImgsModelList = goodsImgsModelList(111);
        String goodsExtModel = goodsExtModel(111);

        Map<String, Object> map = new HashMap<>();
        map.put("goodsDetailModel", goodsDetailModel);
        map.put("goodsImgsModelList", goodsImgsModelList);
        map.put("goodsExtModel", goodsExtModel);

        return map;
    }


    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 异步的方式查询数据
     */
    private Map<String, Object> getDataByAsync() throws InterruptedException, ExecutionException {

        Future<String> goodsDetailModel = executorService.submit(() -> goodsDetailModel(111));
        Future<List<String>> goodsImgsModelList = executorService.submit(() -> goodsImgsModelList(111));
        Future<String> goodsExtModel = executorService.submit(() -> goodsExtModel(111));

        Map<String, Object> map = new HashMap<>();
        map.put("goodsDetailModel", goodsDetailModel.get());
        map.put("goodsImgsModelList", goodsImgsModelList.get());
        map.put("goodsExtModel", goodsExtModel.get());

        executorService.shutdown();
        return map;
    }




}
