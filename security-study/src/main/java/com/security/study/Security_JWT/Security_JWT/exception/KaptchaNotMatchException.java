package com.security.study.Security_JWT.Security_JWT.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 定义验证码异常类
 */
public class KaptchaNotMatchException extends AuthenticationException {
    public KaptchaNotMatchException(String msg, Throwable t) {
        super(msg, t);
    }

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
}
