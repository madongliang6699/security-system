package com.security.study.spring.aop.JDK_CGLIB.JDK.demo;


import com.security.study.spring.aop.JDK_CGLIB.JDK.pojo.*;

public class MyTest {

    public static void main(String[] args) {

        IService iServiceA = CostTimeInvocationHandler.createProxyInstance(new IServiceA());
        iServiceA.m1();
        iServiceA.m2();
        iServiceA.m3();

        IService iServiceB = CostTimeInvocationHandler.createProxyInstance(new IServiceB());
        iServiceB.m1();
        iServiceB.m2();
        iServiceB.m3();

        //这里IServiceC没有实现IService接口，因此报错。
//        IService iServiceC = CostTimeInvocationHandler.createProxyInstance(new IServiceC());
//        iServiceC.m1();
//        iServiceC.m2();
//        iServiceC.m3();




//        我们再有其他接口，也需要统计耗时的功能，此时我们无需去创建新的代理类即可实现同样的功能，如下： IUserService接口
        IUserService userServiceA = CostTimeInvocationHandler.createProxyInstance(new IUserServiceA());
        userServiceA.selectAllUser();

        IUserService userServiceB = CostTimeInvocationHandler.createProxyInstance(new IUserServiceB());
        userServiceB.selectAllUser();

        /**
         * Proxy使用注意:
         * jdk中的Proxy只能为接口生成代理类，如果你想给某个类创建代理类，那么Proxy是无能为力的，此时需要我们用到下面要说的cglib了。
         * Proxy类中提供的几个常用的静态方法大家需要掌握.
         * 通过Proxy创建代理对象，当调用代理对象任意方法时候，会被InvocationHandler接口中的invoke方法进行处理，这个接口内容是关键
         */


    }
}
