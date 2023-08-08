package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;

import java.util.UUID;

/**
 * Dispatcher
 *
 * Dispatcher和LazyLoader作用很相似，区别是用Dispatcher的话每次对增强bean进行方法调用都会触发回调。
 */
public class DispatcherTest1 {
    
    
    public static class UserModel {
        private String name;
        
        public UserModel() {
        }
        public UserModel(String name) {
            this.name = name;
        }
        public void say() {
            System.out.println("你好：" + name);
        }
    }
    
    
    
    
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LazyLoaderTest1.UserModel.class);
        //创建一个Dispatcher对象
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("调用Dispatcher.loadObject()方法");
                return new LazyLoaderTest1.UserModel("路人甲java," + UUID.randomUUID().toString());
            }
        };
        enhancer.setCallback(dispatcher);
        Object proxy = enhancer.create();
        LazyLoaderTest1.UserModel userModel = (LazyLoaderTest1.UserModel) proxy;
        System.out.println("第1次调用say方法");
        userModel.say();
        System.out.println("第2次调用say方法");
        userModel.say();
    
    
        /**
         * 运行输出:
         * 第1次调用say方法
         * 调用Dispatcher.loadObject()方法
         * 你好：路人甲java,17365e39-c1a9-4fc8-8647-48ba8d62a2bd
         * 第2次调用say方法
         * 调用Dispatcher.loadObject()方法
         * 你好：路人甲java,85007b6e-5f4c-4de6-87a7-96396a76bc5d
         *
         */
    }
    
    
    
    
    
}
