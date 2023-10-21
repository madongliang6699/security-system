package com.security.study.并发编程._29_JUC中的CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println(Thread.currentThread().getName() + "线程，run end ...");
        });

        //执行完成之后调用的方法。无论是否有异常都会调用。
        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println(Thread.currentThread().getName() + "线程，执行完成！");
                System.out.println("异常: " + action);
                //其实上面这个action参数就是执行任务抛出的异常，通过判断action是否是null也可以判断是否有抛出异常，
                // 进而做一些处理，这样就可以不使用下面的exceptionally()方法了。如果是null，代表没有异常。
            }
        });

        //执行异常之后调用的方法，先与whenComplete方法执行
        future.exceptionally(t -> {
            System.out.println(Thread.currentThread().getName() + "线程，执行失败！" + t.getMessage());
            return null;
        });


        TimeUnit.SECONDS.sleep(2);
        /**
         * 此类的回调方法，哪怕主线程已经执行结束，已经跳出外围的方法体，
         * 然后回调方法依然可以继续等待异步任务执行完成再触发，丝毫不受外部影响。
         * 也就是说，whenComplete方法和exceptionally方法不阻塞，不受主线程的影响。
         */
    }
}
