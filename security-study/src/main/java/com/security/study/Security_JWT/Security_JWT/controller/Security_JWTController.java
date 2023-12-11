package com.security.study.Security_JWT.Security_JWT.controller;

import com.google.code.kaptcha.Producer;
import com.security.study.Security_JWT.Security_JWT.domain.entity.MyUser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class Security_JWTController {

    //region 依赖注入
    @Resource
    private Producer producer;
    //endregion 依赖注入


    @GetMapping("nihao")
    public String test() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        MyUser user = (MyUser) authentication.getPrincipal();
        System.out.println(user.getUsername());
        System.out.println(user.getAuthorities());
        System.out.println(user.getPassword());

        new Thread(() -> {
            System.out.println("子线程中：" + user.getUsername());

            Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("子线程中authentication2：" + authentication2);//authentication2 是 null
            User user2 = (User) authentication2.getPrincipal();
            System.out.println("子线程中2：" + user2.getUsername());

            /**
             * 运行就报错，空指针。因为authentication2是null
             * 可以看到默认策略，是⽆法在⼦线程中获取⽤户信息，如果需要在⼦线程中获取必须使⽤第
             * ⼆种策略，默认策略是通过 System.getProperty() 系统变量 加载的，因此我们可以通过增加 VM
             * Options 参数进⾏修改,在IDEA的启动配置加参数（-Dspring.security.strategy=MODE_INHERITABLETHREADLOCAL）就行。
             */

        }).start();

        return "buhao";
    }


    @GetMapping("/vc.png")
    public String getVerifyCode(HttpSession session) throws IOException {
        //1.⽣成验证码
        String code = producer.createText();
        session.setAttribute("kaptcha", code); //"可以更换成 redis实现
        BufferedImage bi = producer.createImage(code);
        //2.写⼊内存
        FastByteArrayOutputStream fos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "png", fos);
        //3.⽣成 base64
        return Base64.encodeBase64String(fos.toByteArray());
    }

}
