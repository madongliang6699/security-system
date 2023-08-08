package com.security.study.spring.aop.aopSourceCode;

import com.security.study.spring.aop.aopSourceCode.testData.FundsService;
import com.security.study.spring.aop.aopSourceCode.testData.SendMsgThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;


public class Demo2 {
    
    public static void main(String[] args) {
        /**
         * 案例2：通过异常通知记录异常：
         *通过异常通知来捕获所有方法的运行，发现异常之后，通知开发修复bug。
         */
    
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        /**
         *
        添加一个方法前置通知，判断用户名不是“路人”的时候，抛出非法访问异常
         */
        proxyFactory.addAdvice(new SendMsgThrowsAdvice());
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.aa();//测试在没有设置切点的情况下是不是每个方法都拦截
        proxy.cashOut("xiaoming", 1000000);
        
    }
    
    
}
