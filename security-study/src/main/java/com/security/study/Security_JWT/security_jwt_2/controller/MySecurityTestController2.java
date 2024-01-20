package com.security.study.Security_JWT.security_jwt_2.controller;

import com.security.study.Security_JWT.security_jwt_2.entity.SysUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mySecurityTestController2")
public class MySecurityTestController2 {

    /**
     * 测试基于注解的权限控制，前提是开启在配置类上开启
     *
     * @EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true, jsr250Enabled=true)
     * 注解。其中的三个参数的作用：
     * perPostEnabled: 开启 Spring Security 提供的四个权限注解，@PostAuthorize、@PostFilter、@PreAuthorize 以及@PreFilter。
     * securedEnabled: 开启 Spring Security 提供的 @Secured 注解⽀持，该注解不⽀持权限表达式
     * jsr250Enabled: 开启 JSR-250 提供的注解，主要是@DenyAll、@PermitAll、@RolesAll 同样这些注解也不⽀持权限表达式
     * <p>
     * # 以上注解含义如下:
     * - @PostAuthorize： 在⽬前标⽅法执⾏之后进⾏权限校验。
     * - @PostFiter： 在⽬标⽅法执⾏之后对⽅法的返回结果进⾏过滤。
     * - @PreAuthorize：在⽬标⽅法执⾏之前进⾏权限校验。
     * - @PreFiter：在⽬前标⽅法执⾏之前对⽅法参数进⾏过滤。
     * - @Secured：访问⽬标⽅法必须具各相应的⻆⾊。
     * - @DenyAll：拒绝所有访问。
     * - @PermitAll：允许所有访问。
     * - @RolesAllowed：访问⽬标⽅法必须具备相应的⻆⾊。
     * <p>
     * 这些基于⽅法的权限管理相关的注解，⼀般来说只要设置 prePostEnabled=true 就够⽤了。
     */


    @PreAuthorize("hasRole('admin')")
    @GetMapping("/a1")
    public String a1() {
        System.out.println("-----");
        return "ok";
    }

    //当前登陆的小明用户虽然没有直接拥有user角色，但是其拥有的amdin角色是具备user角色的全部权限的，因为在 MSecurityConfig 配置中配置的。
    //所以这里能访问通。
    @PreAuthorize("hasRole('user')")
    @GetMapping("/a11")
    public String a11() {
        System.out.println("-----");
        return "ok";
    }

    //没有该角色，拒绝访问
    @PreAuthorize("hasRole('order')")
    @GetMapping("/a12")
    public String a12() {
        System.out.println("-----");
        return "ok";
    }

    //没有该角色，拒绝访问
    @PreAuthorize("hasRole('order222')")
    @GetMapping("/a122")
    public String a122() {
        System.out.println("-----");
        return "ok";
    }


    /**
     * jwt方式的认证，应该是没有把用户信息存到session中，进而没有把用户信息存到security的认证上下文中，所以这里的authentication.name实际上是用不了的。
     */
    @PreAuthorize("authentication.name='xiaoming'")
    @GetMapping("/a2")
    public String a2() {

        System.out.println("-----");
        return "ok";
    }

    /**
     * filterTarget的参数 必须是 数组 集合
     */
    @PreFilter(value = "filterObject.id%2!=0", filterTarget = "users")
    @GetMapping("/a3")
    public String a3(@RequestBody List<SysUser> users) {

        /*

        postman的参数给了三个对象：
        [
            {
                "id":"3",
                "username":"xiaoming",
                "password":"xxx"
            },
            {
                "id":"2",
                "username":"xiaoming",
                "password":"xxx"
            },
            {
                "id":"1",
                "username":"xiaoming",
                "password":"xxx"
            }
        ]

        但是这里能接收到的参数中只有两个对象，因为id为2的被上面的注解过滤了。


         */


        System.out.println("-----");
        return "ok";
    }

    /**
     * 这个注解 @PostAuthorize 应该是用于对返回数据做敏感数据的检查，比如一个用户可以访问这个权限，
     * 但是访问出的数据如果有对该用户敏感的信息，就可以用这个注解控制访问受限。
     */
    @PostAuthorize("returnObject.id==1")
    @GetMapping("/a4")
    public SysUser a4() {

        SysUser sysUser = new SysUser();
        sysUser.setUsername("xxx");
//        sysUser.setId(1);//能正常返回数据
        sysUser.setId(2);// 报错：Access is denied 拒绝访问

        return sysUser;
    }


    /**
     * 这个注解 @PostFilter 应该是用于对返回数据做敏感数据的检查过滤。
     */
    @PostFilter("filterObject.id%2==0")
    @GetMapping("/a5")
    public List<SysUser> a5() {

        List<SysUser> users = new ArrayList();
        for (int i = 0; i < 10; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUsername("xxx");
            sysUser.setId(i);
            users.add(sysUser);
        }
        //只能返回id为偶数的对象。
        return users;
    }

    //
//    @Secured({"ROLE_ADMIN","ROLE_USER"}) // 该注解只能判断⻆⾊, 具有其中⼀个角色即可
//    @PermitAll //允许所有访问。
//    @DenyAll //拒绝所有访问。
//    @RolesAllowed：访问⽬标⽅法必须具备相应的⻆⾊。具有其中⼀个⻆⾊即可

}
