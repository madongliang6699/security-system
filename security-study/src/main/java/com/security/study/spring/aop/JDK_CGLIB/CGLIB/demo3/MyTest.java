package com.security.study.spring.aop.JDK_CGLIB.CGLIB.demo3;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;

public class MyTest {

    public static void main(String[] args) {
    
        /**
         * 案例3：拦截所有方法并返回固定值（FixedValue）
         *当调用某个类的任何方法的时候，都希望返回一个固定的值，此时可以使用FixedValue接口，
         * 注意：
         * 1、要注意方法返回值类型要与返回的固定值的类型一致，否则会包类型转换异常。
         * 2、如果使用FixedValue做固定值返回，则被代理对象的方法不再被调用，方法里面的逻辑不再有机会执行。
         * 如下：
         */
    
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "路人甲";
            }
        });
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());//@1
        System.out.println(proxy.m2()); //@2
        System.out.println(proxy.toString());//@3
        System.out.println(proxy);//
        // System.out.println(proxy.hashCode());//
        System.out.println(proxy.m3());//m3()方法的返回值应该是int类型，但是代理拦截器统一返回的是字符串，这里就报类型转换异常错误了。
        
        /*
        @1、@2、@3调用了代理对象的3个方法，运行执行结果：
        路人甲
        路人甲
        路人甲
        路人甲
        Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
            at com.mdl.aop.JDK_CGLIB.CGLIB.demo3.Service3$$EnhancerByCGLIB$$2495c344.m3(<generated>)
            at com.mdl.aop.JDK_CGLIB.CGLIB.demo3.MyTest.main(MyTest.java:35)
         */
        
    
    }
}
