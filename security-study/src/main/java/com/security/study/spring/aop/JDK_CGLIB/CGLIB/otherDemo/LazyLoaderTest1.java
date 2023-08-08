package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

/**
 * LazyLoader的使用
 * <p>
 *
 * LazyLoader是cglib用于实现懒加载的callback。当被增强bean的方法初次被调用时，会触发回调，
 * 之后每次再进行方法调用都是对LazyLoader第一次返回的bean调用，hibernate延迟加载有用到过这个。
 */
public class LazyLoaderTest1 {
    
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
        enhancer.setSuperclass(UserModel.class);
        //创建一个LazyLoader对象
        LazyLoader lazyLoader = new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("调用LazyLoader.loadObject()方法");
                return new UserModel("路人甲java");
            }
        };
        enhancer.setCallback(lazyLoader);
        Object proxy = enhancer.create();
        UserModel userModel = (UserModel) proxy;
        System.out.println("第1次调用say方法");
        userModel.say();
        System.out.println("第2次调用say方法");
        userModel.say();
    
    
        /**
         * 执行结果：
         * 第1次调用say方法
         * 调用LazyLoader.loadObject()方法
         * 你好：路人甲java
         * 第2次调用say方法
         * 你好：路人甲java
         *
         *"调用LazyLoader.loadObject()方法"只打印了一次
         *
         * 当第1次调用say方法的时候，会被cglib拦截，进入lazyLoader的loadObject内部，将这个方法的返回值作为say方法的调用者，
         * loadObject中返回了一个路人甲Java的UserModel，cglib内部会将loadObject方法的返回值和say方法关联起来，然后缓存起来，
         * 而第2次调用say方法的时候，通过方法名去缓存中找，会直接拿到第1次返回的UserModel，所以第2次不会进入到loadObject方法中了。
         *
         *
         */
    
    }
    
    
}
