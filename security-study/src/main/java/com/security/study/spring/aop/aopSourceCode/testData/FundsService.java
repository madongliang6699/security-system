package com.security.study.spring.aop.aopSourceCode.testData;

/**
 * 资金服务
 */
public class FundsService {
    
    //账户余额
    private double balance = 1000;
    
    
    
    //模拟充值
    public double recharge(String userName, double price) {
        System.out.println(String.format("%s充值%s", userName, price));
        balance += price;
        return balance;
    }
    //模拟提现
    public double cashOut(String userName, double price) {
        if (balance < price) {
            throw new RuntimeException("余额不足!");
        }
        System.out.println(String.format("%s提现%s", userName, price));
        balance -= price;
        return balance;
    }
    //获取余额
    double getBalance(String userName) {
        return balance;
    }
    
    
    //测试在没有设置切点的情况下是不是每个方法都拦截
    public void aa() {
        System.out.println("-=-=-=-=-=-=-");
        throw new RuntimeException("sdsdsdsdsd");
    }



}
