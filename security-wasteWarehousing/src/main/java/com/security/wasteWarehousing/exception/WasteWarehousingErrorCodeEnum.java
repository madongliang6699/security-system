package com.security.wasteWarehousing.exception;

import com.security.common.exception.BaseErrorCodeEnum;

/**
 * 异常错误码枚举值
 * 前三位代表服务，后三位代表功能错误码
 *
 * @author zhonghuashishan
 * @version 1.0
 */
public enum WasteWarehousingErrorCodeEnum implements BaseErrorCodeEnum {
    
    WAREHOUSING_XXXX_YYY("200000", "所选的产生单不全是 待处置状态或贮存，请确认"),
    ;

    private String errorCode;

    private String errorMsg;
    
    WasteWarehousingErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}