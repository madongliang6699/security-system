package com.security.market.exception;


import com.security.common.exception.BaseBizException;
import com.security.common.exception.BaseErrorCodeEnum;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public class MarketBizException extends BaseBizException {

    public MarketBizException(String errorMsg) {
        super(errorMsg);
    }

    public MarketBizException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public MarketBizException(BaseErrorCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum);
    }

    public MarketBizException(String errorCode, String errorMsg, Object... arguments) {
        super(errorCode, errorMsg, arguments);
    }

    public MarketBizException(BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) {
        super(baseErrorCodeEnum, arguments);
    }
}