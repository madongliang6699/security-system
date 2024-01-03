package com.security.study.Security_JWT.security_jwt_1.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 定义验证码异常类。<p></>
 * 这里的异常类继承了 AuthenticationException 异常，所以security框架能捕获到该异常并调用设置好的认证失败或成功的Handler
 */
public class KaptchaNotMatchException extends AuthenticationException {
    public KaptchaNotMatchException(String msg, Throwable t) {
        super(msg, t);
    }

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
}
