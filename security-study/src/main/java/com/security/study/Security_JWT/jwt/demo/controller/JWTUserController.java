package com.security.study.Security_JWT.jwt.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.util.concurrent.RateLimiter;
import com.security.study.Security_JWT.jwt.demo.entity.User;
import com.security.study.Security_JWT.jwt.demo.service.UserService;
import com.security.study.Security_JWT.jwt.demo.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class JWTUserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/login")
    public Map<String,Object> login(User user){
        RateLimiter rateLimiter = RateLimiter.create(5, 23, TimeUnit.SECONDS);
        rateLimiter.acquire();
        rateLimiter.setRate(10);

        log.info("用户名: [{}]",user.getName());
        log.info("密码: [{}]",user.getPassword());
        Map<String, Object> map = new HashMap<>();
        try{
            User userDB = userService.login(user);
            Map<String,String> payload =  new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("name",userDB.getName());
            //生成JWT的令牌
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);//响应token
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }


    @PostMapping("/user/userList")
    public User test(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //处理自己业务逻辑
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        log.info("用户id: [{}]",verify.getClaim("id").asString());
        log.info("用户name: [{}]",verify.getClaim("name").asString());
        User userDB = new User();
        userDB.setId("12");
        userDB.setName("xiaoming");
        userDB.setPassword("123");
        return userDB;
    }


}
