package com.security.study.Security_JWT.Security_JWT.controller;

import com.google.code.kaptcha.Producer;
import com.security.study.Security_JWT.Security_JWT.domain.entity.MyUser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

import static com.security.study.Security_JWT.Security_JWT.filter.LoginKaptchaFilter.VERIFICATION_CODE;

@RestController
public class Security_JWTController {

    //region 依赖注入
    @Resource
    private Producer producer;
    //endregion 依赖注入


    @GetMapping("/nihao")
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
             * 可以看出：默认策略，是⽆法在⼦线程中获取⽤户信息，如果需要在⼦线程中获取必须使⽤第
             * ⼆种策略“MODE_INHERITABLETHREADLOCAL”，默认策略是通过 System.getProperty() 系统变量 加载的，因此我们可以通过增加 VM
             * Options 参数进⾏修改,在IDEA的启动配置加参数（-Dspring.security.strategy=MODE_INHERITABLETHREADLOCAL）就行。
             *
             * 不过在实际开发中，如果想在子线程中使用用户数据，那先在主线程中获取到用户登录信息，开启子线程或线程池的时候，作为参数传进去就行了。
             */

        }).start();

        return "buhao";
    }


    /**
     * 这个接口是给前端调用生产验证码的，返回给前端的是一个图片的Base64格式的数据，前端可以解码展示成图片。
     * 然后用户登录的时候，在⾃定义LoginKaptchaFilter中加⼊验证码验证的逻辑。
     */
    @GetMapping("/vc.png")
    public String getVerifyCode(HttpSession session) throws IOException {
        //1.⽣成验证码
        String code = producer.createText();
        //2.这里是将对应用户的验证码先存到对应的session中，等待前端登录的时候验证。
        session.setAttribute(VERIFICATION_CODE, code); //可以更换成 redis实现
        //3.讲验证码转换成图片
        BufferedImage bufferedImage = producer.createImage(code);
        //4.将图片对象写入内存数据流
        FastByteArrayOutputStream fos = new FastByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", fos);//jpg格式的也行
        //3.⽣成 base64字符串数据 给前端。【https://www.toolscat.com/img/base64-img 这个网站可以解析这里拿到的base64字符串数据成图片】
        return Base64.encodeBase64String(fos.toByteArray());
    }


    public static void main(String[] args) {

        /**
         不同密码加密方法的测试：
         */
        //1.BCryptPasswordEncoder 推荐使用的。这种加密算法能加大消耗计算机资源，导致运行比较慢，增加暴力破解的难度。
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));//$2a$10$SPGbmmH.iFx.4RpSImq54.indJlU4wAL9Ri50xqjq49sI9JHURbs6 【默认是10】
        BCryptPasswordEncoder bc16 = new BCryptPasswordEncoder(16);
        System.out.println(bc16.encode("123"));// 【设置16位】
        BCryptPasswordEncoder bc16加盐 = new BCryptPasswordEncoder(16, new SecureRandom("haha".getBytes()));
        System.out.println(bc16加盐.encode("123"));//加盐

        BCryptPasswordEncoder aaa = new BCryptPasswordEncoder();
        System.out.println("111: "+aaa.encode("111"));




        //2.Pbkdf2PasswordEncoder
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        System.out.println(pbkdf2PasswordEncoder.encode("123"));

        //3.SCryptPasswordEncoder todo 需要额外引⼊依赖
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        System.out.println(sCryptPasswordEncoder.encode("123"));

        //4.Argon2PasswordEncoder todo 需要额外引⼊依赖
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        System.out.println(argon2PasswordEncoder.encode("123"));
    }

}
