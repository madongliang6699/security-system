package com.security.study.spring.Bean生命周期详解.demo12;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DestroyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DestroyConfig.class);
        //启动容器
        System.out.println("准备启动容器");
        context.refresh();
        System.out.println("容器启动完毕");
        System.out.println("serviceC：" + context.getBean(ServiceC_230722.class));
        //关闭容器
        System.out.println("准备关闭容器");
        //调用容器的close方法，会触发bean的销毁操作
        context.close(); //@2 关闭容器，触发bean销毁操作
        System.out.println("容器关闭完毕");
    
        /**
         * 打印：
         *
         * 准备启动容器
         * 创建ServiceC实例
         * 容器启动完毕
         * serviceC：com.mdl.Bean生命周期详解.demo12.ServiceC_230722@295cf707
         * 准备关闭容器
         * preDestroy2()
         * preDestroy1()
         * DisposableBean接口中的destroy()
         * 我是自定义的销毁方法:customDestroyMethod()
         * 容器关闭完毕
         *
         *
         * 可以看出销毁方法调用的顺序依次是：
         * @PreDestroy标注的所有方法
         * DisposableBean接口中的destroy()
         * 自定义的销毁方法
         */
    }
}
