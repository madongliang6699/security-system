package com.security.wasteWarehousing.exception;

import com.security.common.exception.BaseBizException;
import com.security.common.exception.BaseErrorCodeEnum;

/**
 * 入库单业务异常
 */
public class WasteWarehousingBizException extends BaseBizException {
    
    
    public WasteWarehousingBizException(String errorMsg) {
        super(errorMsg);
    }
    
    public WasteWarehousingBizException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
    
    public WasteWarehousingBizException(BaseErrorCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum);
    }
    
    public WasteWarehousingBizException(String errorCode, String errorMsg, Object... arguments) {
        super(errorCode, errorMsg, arguments);
    }
    
    public WasteWarehousingBizException(BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) {
        super(baseErrorCodeEnum, arguments);
    }
}
