package com.security.common.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {


    //加密盐
    private static final String  SING = "mdlTest";

    /**
     * 生成token  header.payload.sing
     * <p>登录的时候使用此方法获取token给前端返回。<p/>
     */
    public static String getToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);//默认7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));//sign
        return token;
    }

    /**
     * 验证token 合法性。并返回token内涵的用户信息。
     *
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token里的用户信息
     */
    public static Map<String, Object> getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        //todo 这里应该通过通用的方式拿到所有信息，而不是固定获取已经定好的key-val吧。待补充
        Map<String, Object> map = new HashMap<>();
        Integer aa = verify.getClaim("aa").asInt();
        map.put("aa", aa);
        return map;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "123");
        map.put("userName", "xiaoming");
        String token = getToken(map);
        System.out.println(token);
    }
}
