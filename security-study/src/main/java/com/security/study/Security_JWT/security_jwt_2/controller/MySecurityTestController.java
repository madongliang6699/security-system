package com.security.study.Security_JWT.security_jwt_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试登录认证 和 测试基于url的权限控制。
 */
@RestController
public class MySecurityTestController {

    /**
     * 说明：security_jwt_2文件夹中的 认证相关 的代码是基于网络上的文章和gitee中的开源项目总结的。【https://gitee.com/micai-code/springboot-springsecurity-jwt-demo/blob/develop/src/main/java/boss/portal/filter/JWTAuthenticationFilter.java】
     * 也夹杂了一些security_jwt_1文件夹中的东西。
     *
     * 而权限控制是基于security_jwt_1文件夹中的老师笔记的权限章节的内容总结的下面的代码。
     *
     */

    /**
     * security的权限控制的基本思想原理就是RBAC（Role-Based Access Control 或者 Resource-Based Access Control），基于⻆⾊权限管理和基于资源权限管理.
     *
     * 授权核⼼概念
     * 在前⾯学习认证过程中，我们得知认证成功之后会将当前登录⽤户信息保存到
     * Authentication 对象中，Authentication 对象中有⼀个 getAuthorities() ⽅
     * 法，⽤来返回当前登录⽤户具备的权限信息，也就是当前⽤户具有权限信息。该⽅法的返回
     * 值为 Collection<? extends GrantedAuthority>，当需要进⾏权限判断时，就会根
     * 据集合返回权限信息调⽤相应⽅法进⾏判断。
     * 【我们的用户实体类 SysUser 中就实现了 getAuthorities() 这个方法，也就是相当于用户的角色或者权限信息是保存到了用户对象中，当需要用到当前用户的角色或者
     * 权限做判断时，就调用这个方法获取获取。至于返回的是角色还是权限，就看你是基于角色的权限设计还是基于资源权限的设计。具体可以看一下笔记中的讲解。】
     *
     * security权限控制从使用方法上来说，基本分两大类：
     * 基于url的控制：
     *   在WebSecurityConfig配置类中，可以配置基于url的控制。
     *   比如：
     *   .antMatchers("/admin/**").hasRole("admin")
     *   .antMatchers("/user/**").hasRole("user")
     *
     * 基于角色的控制：
     *      主要是在方法上使用注解的方式，在注解中指定能访问该方法的角色。看 MySecurityTestController2 类。
     *
     */

    @GetMapping("/all/a1")
    public String alla1() {
        System.out.println("-----");
        return "/all/a1";
    }


    @GetMapping("/all/a2")
    public String alla2() {
        System.out.println("-----");
        return "/all/a2";
    }

    @GetMapping("/one/a1")
    public String onea1() {
        System.out.println("-----");
        return "/one/a1";
    }


    @GetMapping("/admin/a1")
    public String admina1() {
        System.out.println("-----");
        return "/admin/a1";
    }


    @GetMapping("/admin/a2")
    public String admina2() {
        System.out.println("-----");
        return "/admin/a2";
    }


    @GetMapping("/user/a2")
    public String usera2() {
        System.out.println("-----");
        return "/user/a2";
    }

}
