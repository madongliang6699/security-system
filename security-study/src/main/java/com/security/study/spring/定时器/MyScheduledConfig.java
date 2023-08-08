package com.security.study.spring.定时器;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@Configuration
public class MyScheduledConfig implements SchedulingConfigurer {

    /**
     * 自定义定时器线程池的方法，当然看过源码之后，应该有很多种方法去自定义自己的线程池，
     * 不过现在源码还没研究透，先举例这么两个方法吧。其实都是根据源码的内部使用线程池的逻辑，
     * 自己预先设置好线程池供spring定时器直接使用，而不是让spring自己造一个默认线程池。spring内部自己造的线程池是
     * newSingleThreadScheduledExecutor：产生一个ScheduledExecutorService对象，这个对象的线程池大小为1，
     * 如果任务多于一个，任务将按先后顺序执行。
     *
     *
     * 给spring的定时任务设置线程池的方法：
     * 第一种；就是直接实例化一个TaskScheduler或者ScheduledExecutorService或者ScheduledThreadPoolExecutor或者ThreadPoolTaskScheduler都行，
     * 这些要么是接口类型要么是类，看名字就知道都是和定时器有关的线程池的接口或者类。实际上定时器执行的时候就去找这些接口的或者实现类的bean，如果有就用了，如果没有就自己造一个
     * 上面说的newSingleThreadScheduledExecutor。
     *
     * 第二种方法：
     * 就是实现SchedulingConfigurer接口，重写configureTasks给taskRegistrar设置进去一个Scheduler相关的线程池，这样也行。
     * 看样子，第一种方法的实例化的bean应该就是给第二种方法的taskRegistrar使用的，只不过第二种方法使我们手动set进去的。
     *
     */


//    @Bean()
//    public ScheduledThreadPoolExecutor myTaskScheduler2() {
//        //可以设置需要并行执行的任务数量
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(6);
//        return executor;
//    }

//    @Bean()
//    public ThreadPoolTaskScheduler myTaskScheduler() {
//        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
//        executor.setPoolSize(5);
////        executor.setThreadNamePrefix("haha---");
//        return executor;
//    }





    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(5);
        executor.setThreadNamePrefix("haha---");
        executor.initialize();//todo 这里必须初始化一下是什么意思。或者把ThreadPoolTaskScheduler单独使用@Bean创建，这样他可以自动初始化。
        taskRegistrar.setScheduler(executor);
    }


}
