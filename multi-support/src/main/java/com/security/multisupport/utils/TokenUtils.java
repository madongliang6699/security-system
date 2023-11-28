package com.security.multisupport.utils;

import com.security.multisupport.db.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
//import org.slf4j.//logger;
//import org.slf4j.//loggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;


/**
 * Token序列化和反序列化支持
 *
 * @author zeng
 * @version v 0.1
 * @File TokenUtils.java
 * @DateTime 下午3:19
 * @Encoding UTF-8
 * @Description Copyright (c) 2004-2019 All Rights Reserved.
 */
public class TokenUtils {

//    private static final //logger  = //loggerFactory.get//logger(TokenUtils.class);

    /**
     * 设置发行人
     */
    private static final String ISSUER = "cybstar";
    /**
     * 设置抽象主题
     */
    private static final String SUBJECT = "login_token";

    /**
     * 默认TokenKey值
     */
    private static final String DEFAULT_TONKE_KEY = "rKYM0eaAc99uCtsMLLS9GQq9ty5q1yuRLmqFvxgQFfEhtqWxk+ctceVZlZ3Euh+Cx1b1wSM5VPUz66CkmwNdqwcyb==";

    /**
     * HS256 私钥
     */
    private static final String HS256KEY = DEFAULT_TONKE_KEY;

    /**
     * 秘钥格式
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 秘钥对象
     */
    private static final Key SIGNING_KEY = new SecretKeySpec(Base64.decodeBase64(HS256KEY), SIGNATURE_ALGORITHM.getJcaName());

    /**
     * Token文本RSA公钥
     */
    private static final String TOKEN_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU0HErFIuj54Y1X4vDcGLvxptPgv07gXFhCrwM270frVWcPIGpBBRujUKggVR+eLN1nwvWqPvnAkF1x6iwSAihjyqHiVTRFnKGYaDub7Et05ZmsRUToIXKdYQHplbVTOw3e8ujpdD0zSvRaGS7OXBs8iWPWY/h032JoFRld0ZO6QIDAQAB";


    /**
     * Token文本RSA私钥
     */
    private static final String TOKEN_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJTQcSsUi6PnhjVfi8NwYu/Gm0+C/TuBcWEKvAzbvR+tVZw8gakEFG6NQqCBVH54s3WfC9ao++cCQXXHqLBICKGPKoeJVNEWcoZhoO5vsS3TlmaxFROghcp1hAemVtVM7Dd7y6Ol0PTNK9FoZLs5cGzyJY9Zj+HTfYmgVGV3Rk7pAgMBAAECgYBjA3FYvZnPm1tjpcfjaKKbcOSjeb8t2YlrjO3kDEyAiB7fDi8jGrDRgbGkA4kJgRu+le0VjWfVo12UlRy69aKvf3XpciOxSfGuCMfKdM4MuJIrMMmKK2v+iXVfL1Ca1yyPPEK2Pcdxy1nOBa7qMTmUJHKRGrvno383hR0Xcnvk1QJBANN2slHWgQF3TYJNTayoe2qAEAr+v7F+niA1IpzIr6HakOAR1DeN7FZYWf72P5Q9eFrV4RdH5LDoD8lJPQzrvAsCQQC0J+4h7EcjCVL7vidgthWneNFwSbpMd9RxgzBSUHv7ugmEJI1BzzD09WFgI2mmdO4oCsPWqXR/5Yf/p6AdEsVbAkEAr0lj9Uye8U0olctoiKe8bgKrycFzuzje8Im7IEWGuN7JWsPMqyRc9RIVv6/18fambn1+MWMp4a7rbwnjrnM2EwJAA4eUvs1mR2VzXsNG+joXCoTvdYe8QqtGWkL7u2EgTLpEXXZp3hQ1HVeBZOTMuRopYFd1pssDIU5Z78RU+rzXaQJBAIq/Z5bmi39fpKcMe9+bgba7MUrLwBiqcvhPSY+hL8bOS4LoEyEFborOylOnafUR5R/VQQLpM2Q3sflPZVn5uF4=";
    /**
     * 获取HS256KEY
     *
     * @return
     */
    public static String getHS256KEY() {
        return HS256KEY;
    }

    // region 验证


    /**
     * 验证Token
     *
     * @param token Token字符串
     * @return true:验证通过 | false: 验证不通过
     */
    public static boolean isValid(String token) {
        if (StringUtil.isEmpty(token)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            //logger.error("Token文本验证失败，Token文本:[ {} ]", token, e);
            return false;
        }
    }

    // endregion 验证

    // region TokenMap


    /**
     * 格式化Token字符串获取Token组成对象
     *
     * @param token Token字符串
     * @return Token组成对象
     */
    public static Map<String, Object> parseJWTtoMap(String token) {
        Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token.trim()).getBody();
        return claims;
    }

    /**
     * 通过Token组成对象生成Token字符串
     *
     * @param claims Token组成对象
     * @return Token字符串
     */
    public static String getJWTString(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        claims.put(Claims.ISSUER, ISSUER);
        claims.put(Claims.ISSUED_AT, new Date(nowMillis));
        claims.put(Claims.SUBJECT, SUBJECT);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        jwtBuilder.signWith(SIGNATURE_ALGORITHM, SIGNING_KEY);
        return jwtBuilder.compact();
    }


    // endregion TokenMap

    // region 构造TokenInfo对象

    /**
     * 通过Token组成对象生成Token字符串
     *
     * @param tokenInfo Token对象
     * @return Token字符串
     */
    public static String getTokenStrFromTokenInfo(TokenInfo tokenInfo) {
        if (tokenInfo == null) {
            return StringUtil.EMPTY;
        }
        Map<String, Object> claims = tokenInfo.getFieldMap();
        long nowMillis = System.currentTimeMillis();
        claims.put(Claims.ISSUER, ISSUER);
        claims.put(Claims.ISSUED_AT, new Date(nowMillis));
        claims.put(Claims.SUBJECT, SUBJECT);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        jwtBuilder.signWith(SIGNATURE_ALGORITHM, SIGNING_KEY);
        return jwtBuilder.compact();
    }

//    /**
//     * 通过Token组成对象生成Token字符串密文
//     *
//     * @param tokenInfo Token对象
//     * @return Token字符串
//     */
//    public static MicroObjectResp<String> getCipherTokenStrFromTokenInfo(TokenInfo tokenInfo) {
//        String tokenInfoStr = getTokenStrFromTokenInfo(tokenInfo);
//        try {
//            return RespInfoUtil.getSuccessMicroObjectRespInfo(RSAUtils.encryptByPublicKey(tokenInfoStr,TOKEN_PUBLIC_KEY));
//        } catch (Exception e) {
//            String tmpError = StringUtil.formatString("Token文本加密失败，Token文本:[ {} ]", tokenInfoStr);
//            //logger.error(tmpError, e);
//            return RespInfoUtil.getErrorMicroObjectRespInfo(String.class, tmpError, tmpError);
//        }
//    }


    /**
     * 格式化Token字符串获取Token组成对象
     *
     * @param token Token字符串
     * @return Token组成对象
     */
    public static TokenInfo parseJWTtoTokenInfo(String token) {
        TokenInfo tokenInfo = new TokenInfo();
        if (StringUtil.isEmpty(token)) {
            return tokenInfo;
        }
        tokenInfo.setToken(token);
        Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token.trim()).getBody();
        if (claims.containsKey("appId")) {
            tokenInfo.setAppId(claims.get("appId", Integer.class));
        }
        if (claims.containsKey("userId")) {
            tokenInfo.setUserId(claims.get("userId", Long.class));
        }
        if (claims.containsKey("userCode")) {
            tokenInfo.setUserCode(claims.get("userCode", String.class));
        }
        if (claims.containsKey("userName")) {
            tokenInfo.setUserName(claims.get("userName", String.class));
        }
        if (claims.containsKey("userType")) {
            tokenInfo.setUserType(claims.get("userType", String.class));
        }
        if (claims.containsKey("loginDate")) {
            tokenInfo.setLoginDate(claims.get("loginDate", Date.class));
        }
        if (claims.containsKey("expTime")) {
            tokenInfo.setExpTime(claims.get("expTime", Date.class));
        }
        if (claims.containsKey("headerAppId")) {
            tokenInfo.setHeaderAppId(claims.get("headerAppId", Integer.class));
        }
        if (claims.containsKey("roles")) {
            tokenInfo.setRoles((List<Long>) claims.get("roles", List.class));
        }
        if (claims.containsKey("clientFlag")) {
            tokenInfo.setClientFlag(claims.get("clientFlag", String.class));
        }
        return tokenInfo;
    }

    /**
     * 格式化Token字符串密文获取Token组成对象
     *
     * @param token Token字符串
     * @return Token组成对象
     */
//    public static TokenInfo parseCipherJWTtoTokenInfo(String token) {
//        String tmpPlainToken = token;
//        try {
//            tmpPlainToken = RSAUtils.decryptByPrivateKey(token,TOKEN_PRIVATE_KEY);
//        } catch (Exception e) {
//            String tmpError = StringUtil.formatString("Token密文解密失败，Token文本:[ {} ]", token);
//            //logger.error(tmpError);
//        }
//        return parseJWTtoTokenInfo(tmpPlainToken);
//    }

    // endregion 构造TokenInfo对象

}
