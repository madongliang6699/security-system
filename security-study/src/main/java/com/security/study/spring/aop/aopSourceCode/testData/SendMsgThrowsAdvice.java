package com.security.study.spring.aop.aopSourceCode.testData;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SendMsgThrowsAdvice implements ThrowsAdvice {
    
    //注意方法名称必须为afterThrowing, 因为ThrowsAdvice没有方法，方法是通过反射创建的。实现类必须实现以下形式的方法，前3个参数是可选的，最后一个参数为需要匹配的异常的类型。
    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
        //监控到异常后发送消息通知开发者
        System.out.println("异常警报：");
        System.out.println(String.format("method:[%s]，args:[%s]", method.toGenericString(), Arrays.stream(args).collect(Collectors.toList())));
        System.out.println(e.getMessage());
        System.out.println("请尽快修复bug！");
    }
    


}
