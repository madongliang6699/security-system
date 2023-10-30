package com.security.study.并发编程._31_等待线程完成的方式你知道几种;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo2 {

    //用于封装结果
    static class Result<T> {
        T result;
        public T getResult() {
            return result;
        }
        public void setResult(T result) {
            this.result = result;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //用于存放子线程执行的结果
        Demo1.Result<Integer> result = new Demo1.Result<>();
        //创建一个子线程
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                result.setResult(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        });
        thread.start();
        //countDownLatch.await()会让当前线程阻塞，当countDownLatch中的计数器变为0的时候，await方法会返回
        countDownLatch.await();
        //获取thread线程的执行结果
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);
    }

}
