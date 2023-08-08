package com.security.study.spring.aop.JDK_CGLIB.JDK.pojo;

public class IUserServiceA implements IUserService{


    @Override
    public void selectAllUser() {
        System.out.println("查询到所有用户A。。。。。");
    }
}
