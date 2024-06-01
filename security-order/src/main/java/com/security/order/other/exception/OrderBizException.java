package com.security.order.other.exception;


import com.security.common.exception.BaseBizException;
import com.security.common.exception.BaseErrorCodeEnum;

/**
 * 订单中心自定义业务异常类
 *
 */
public class OrderBizException extends BaseBizException {

    public OrderBizException(String errorMsg) {
        super(errorMsg);
    }

    public OrderBizException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public OrderBizException(BaseErrorCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum);
    }

    public OrderBizException(String errorCode, String errorMsg, Object... arguments) {
        super(errorCode, errorMsg, arguments);
    }

    public OrderBizException(BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) {
        super(baseErrorCodeEnum, arguments);
    }
}