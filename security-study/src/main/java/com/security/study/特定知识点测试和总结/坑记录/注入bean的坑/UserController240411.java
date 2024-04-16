package com.security.study.特定知识点测试和总结.坑记录.注入bean的坑;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController240411 {

    //@Resource  //报错: Error creating bean with name 'userController240411': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'userService240411' is expected to be of type 'com.security.study.特定知识点测试和总结.坑记录.注入bean的坑.HseUserService240411' but was actually of type 'com.security.study.特定知识点测试和总结.坑记录.注入bean的坑.UserService240411'
    //HseUserService240411 userService240411;
    //上面这里报错是因为 注入 userService240411的时候, @Resource 默认是通过名称查找bean,
    // 查到到userService240411名称的bean却不是HseUserService240411类的,而是UserController240411类的,就报错了.
    // 一般名称简略写的话是没问题的,因为一般没有正好和"userService240411"同名的bean,但是这里正好有,就报错了.
    // 如果想不报错可以把上面注入的名称写全"hseUserService240411",或者使用@Autowired注入,@Autowired是默认用类型注入的.



    //=======//好在上面这个报错在启动的时候就报错了,下面这个mybatis Mapper在启动的时候可能不报错(公司项目启动的时候这里没没报错,但是这里启动就报错了),=======================================================================================================

//    @Resource
//    HseUserMapper240411 userMapper240411;



}
