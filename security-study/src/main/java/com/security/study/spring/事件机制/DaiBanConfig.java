package com.security.study.spring.事件机制;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

//@EnableAsync
//@Configuration
public class DaiBanConfig {

    /**
     * 让事件模式异步执行：
     * https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648934522&idx=1&sn=7653141d01b260875797bbf1305dd196&chksm=88621044bf15995257129e33068f66fc5e39291e159e5e0de367a14e0195595c866b3aaa1972&token=1081910573&lang=zh_CN&scene=21#wechat_redirect
     * 原理就是spring默认的发布事件的类是SimpleApplicationEventMulticaster，其实例名字是applicationEventMulticaster，
     * 在spring容器实例化的时候就判断容器中是否有applicationEventMulticaster这个名字的bean，如果没有就默认实例化SimpleApplicationEventMulticaster。
     * SimpleApplicationEventMulticaster类内部有个线程池，名字是taskExecutor，taskExecutor默认是null，在发布事件的时候，如果
     * taskExecutor是null，就执行执行发布动作通知监听器执行代码，也就是同步执行。如果taskExecutor不是null，就用taskExecutor线程池异步调用监听器。
     * 进而达到监听器方法的代码异步执行的效果。（看发布者发布方法的源码就知道上面的逻辑了）
     * 明白的了上面的原理，像做到监听器代码异步执行，就好办了：
     * 我们自己先实例化一个SimpleApplicationEventMulticaster对象名字叫applicationEventMulticaster，然后给applicationEventMulticaster内部的
     * taskExecutor线程池赋值一个我们自己准备好的线程池对象就行了。就是下面的代码。
     *
     *
     *
     * todo 切记，如果事件模式设置了异步模式，那@Order注解的排序功能就自然而然的不起作用了
     *
     * 思考：
     * 如果给默认的事件发布者（applicationEventMulticaster）设置了线程池，那只要是这个发布者发布的事件，都会异步执行，那如果我想随意的控制
     * 哪些监听是异步的哪些监听是同步的，应该怎么办，是不是应该自己再构建一个自己的EventMulticaster类，让这个类去发布事件，发布的时候，
     * 调用监听器的方法的时候用同步执行就行了。
     * 或者简单一点：事件模式不用下面这种设置线程池的方式让事件执行异步，而是直接在监听器方法上使用@Async注解实现异步执行，
     * 这样可以随意控制哪个方法是异步的。
     *
     * 所以说：spring的事件模式，要想做到异步，大概有两种方式，一种是直接像下面的代码一样，给applicationEventMulticaster设置一个线程池，
     * 第二种就是，自己通过@Async注解实现异步执行。
     * 当然，在执行监听器的方法代码的时候，直接new一个线程执行也一样。
     *
     */

//    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster(@Autowired ThreadPoolExecutorFactoryBean factoryBean){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(factoryBean.getObject());
        return eventMulticaster;
    }


//    @Bean
    public ThreadPoolExecutorFactoryBean taskExecutor(){
        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setThreadNamePrefix("我的线程--");
        threadPoolExecutorFactoryBean.setCorePoolSize(3);
        threadPoolExecutorFactoryBean.setMaxPoolSize(9);//todo 这个为什么不起作用
        return threadPoolExecutorFactoryBean;
    }




}
