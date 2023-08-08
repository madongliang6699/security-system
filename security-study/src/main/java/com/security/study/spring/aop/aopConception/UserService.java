package com.security.study.spring.aop.aopConception;


/**
 * @author A
 */
public class UserService {
    
    
    public void work(String userName) {
        System.out.println(userName + ",正在工作.......");
        // int i = 3 / 0;
        throw new RuntimeException("测试异常。。。");
    }
    
    
    
}
