package com.security.multisupport.utils;

/**
 * @author zeng on 17-3-20.
 */


import com.security.multisupport.db.RespInfo;
import com.security.multisupport.db.ServiceErrorCodeEnum;

import java.util.List;

/**
 * 返回对象支持工具类
 *
 * @author zeng
 * @File RespInfoUtil.java
 * @Date 2018-12-06
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class RespInfoUtil {

    // region RespInfo构造支持


    /**
     * Http请求方法名错误
     *
     * @return 系统标准响应对象
     */
    public static RespInfo methodNameError() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.MethodNameError);
    }

    /**
     * Tonken未传
     *
     * @return 系统标准响应对象
     */
    public static RespInfo tokenIsMissing() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.TokenIsMissing);
    }

    /**
     * Tonken错误
     *
     * @return 系统标准响应对象
     */
    public static RespInfo tokenError() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.TokenIsOverdue);
    }

    /**
     * 缺少主键
     *
     * @return 系统标准响应对象
     */
    public static RespInfo primaryKeyError() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.PrimaryKeyIsMissing);
    }


    /**
     * 成功
     *
     * @return 系统标准响应对象
     */
    public static RespInfo success() {

        return success(null, null);
    }


    /**
     * 成功
     *
     * @param msg 返回对象
     * @return 系统标准响应对象
     */
    public static RespInfo success(String msg) {
        return success(msg, null);
    }

    /**
     * 成功
     *
     * @param object 返回对象
     * @return 系统标准响应对象
     */
    public static RespInfo success(Object object) {
        return success(null, object);
    }

    /**
     * 成功
     *
     * @param message 业务执行消息
     * @param object  返回对象
     * @return 系统标准响应对象
     */
    public static RespInfo success(String message, Object object) {
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(ServiceErrorCodeEnum.SUCCESS.getErrorCode());
        respInfo.setError(StringUtil.EMPTY);
        respInfo.setMsg(StringUtil.isEmpty(message) ? StringUtil.EMPTY : message);
        respInfo.setValue(object == null ? StringUtil.EMPTY : object);
        return respInfo;
    }


    /**
     * 成功
     *
     * @param error   业务执行错误信息
     * @param message 业务执行消息
     * @param object  返回对象
     * @return 系统标准响应对象
     */
    public static RespInfo success(String error, String message, Object object) {
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(ServiceErrorCodeEnum.SUCCESS.getErrorCode());
        respInfo.setError(StringUtil.isEmpty(message) ? StringUtil.EMPTY : error);
        respInfo.setMsg(StringUtil.isEmpty(message) ? StringUtil.EMPTY : message);
        respInfo.setValue(object == null ? StringUtil.EMPTY : object);
        return respInfo;
    }

    /**
     * 入参格式化错误
     *
     * @param message 错误提示
     * @return 系统标准响应
     */
    public static RespInfo requestFormatError(String message, Object object) {
        RespInfo respInfo = getRespInfoFromCode(ServiceErrorCodeEnum.RequestFormatError);
        respInfo.setMsg(StringUtil.isEmpty(message) ? ServiceErrorCodeEnum.RequestFormatError.getErrorStr() : message);
        respInfo.setValue(object == null ? StringUtil.EMPTY : object);
        return respInfo;
    }

    /**
     * 入参格式化错误
     *
     * @param message 错误提示
     * @return 系统标准响应
     */
    public static RespInfo requestFormatError(String message) {
        return requestFormatError(message, null);
    }

    /**
     * 常规错误
     *
     * @return 系统标准响应对象
     */
    public static RespInfo normalError() {
        return normalError(null, null);
    }

    /**
     * 常规错误
     *
     * @param errorMsg 错误信息
     * @return 系统标准响应对象
     */
    public static RespInfo normalError(String errorMsg) {
        return normalError(errorMsg, null);
    }


    /**
     * 常规错误
     *
     * @param msg   错误信息
     * @param error 详细错误信息
     * @return 系统标准响应对象
     */
    public static RespInfo normalError(String msg, String error) {
        RespInfo respInfo = getRespInfoFromCode(ServiceErrorCodeEnum.ERROR);
        respInfo.setMsg(StringUtil.isEmpty(msg) ? ServiceErrorCodeEnum.ERROR.getErrorStr() : msg);
        respInfo.setError(StringUtil.isEmpty(error) ? StringUtil.EMPTY : error);
        return respInfo;
    }

    /**
     * 常规错误
     *
     * @param errorMsg 错误信息
     * @return 系统标准响应对象
     */
    public static RespInfo normalError(String errorMsg, Object object) {
        RespInfo respInfo = getRespInfoFromCode(ServiceErrorCodeEnum.ERROR);
        respInfo.setMsg(StringUtil.isEmpty(errorMsg) ? ServiceErrorCodeEnum.ERROR.getErrorStr() : errorMsg);
        respInfo.setError(object == null ? StringUtil.EMPTY : object.toString());
        return respInfo;
    }

    /**
     * 自定义错误
     *
     * @param errorMsg 错误信息
     * @return 系统标准响应对象
     */
    public static RespInfo customeError(String errorMsg, int code, Object object) {
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(code);
        respInfo.setError(object == null ? null : object.toString());
        respInfo.setMsg(errorMsg);
        return respInfo;
    }

    /**
     * 系统内部错误
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo normalSysError(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.SysError);
    }


    /**
     * 手机号码错误
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo phoneNumberError(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.PhoneNumberError);
    }

    /**
     * 账户未找到
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo userNotFoundError(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.UserNotFoundError);
    }

    /**
     * 账户名或密码为空
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo userNameOrPasswordIsEmpty(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.UserNameOrPasswordIsEmpty);
    }

    /**
     * 账户名ID已被注册
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo accountNoIsRegistered(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.AccountNoIsRegistered);
    }

    /**
     * 用户名或密码错误
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo userNameOrPasswordError(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.UserNameOrPasswordError);
    }

    /**
     * 账户锁定
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo userStatusError(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.UserStatusError);
    }

    /**
     * 常规错误
     *
     * @param busMsg               错误信息
     * @param serviceErrorCodeEnum 系统错误枚举
     * @return 系统标准响应对象
     */
    public static RespInfo businessError(String busMsg, ServiceErrorCodeEnum serviceErrorCodeEnum) {
        RespInfo respInfo = getRespInfoFromCode(serviceErrorCodeEnum);
        respInfo.setMsg(busMsg);
        return respInfo;
    }

    /**
     * 常规错误
     *
     * @param busMsg               错误信息
     * @param serviceErrorCodeEnum 系统错误枚举
     * @param object               响应值
     * @return 系统标准响应对象
     */
    public static RespInfo businessError(String busMsg, ServiceErrorCodeEnum serviceErrorCodeEnum, Object object) {
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(serviceErrorCodeEnum.getErrorCode());
        respInfo.setError(serviceErrorCodeEnum.getErrorStr());
        respInfo.setMsg(busMsg);
        respInfo.setValue(object);
        return respInfo;
    }

    /**
     * 常规错误
     *
     * @param busMsg               错误信息
     * @param serviceErrorCodeEnum 系统错误枚举
     * @param respInfo             系统响应对象
     * @return 系统标准响应对象
     */
    public static void businessError(Object object, String busMsg, ServiceErrorCodeEnum serviceErrorCodeEnum, RespInfo respInfo) {
        respInfo.setCode(serviceErrorCodeEnum.getErrorCode());
        respInfo.setError(serviceErrorCodeEnum.getErrorStr());
        respInfo.setMsg(busMsg);
        respInfo.setValue(object);
    }

    /**
     * 服务未找到
     *
     * @return 系统标准响应对象
     */
    public static RespInfo serversIsNotFound() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.ServersIsNotFound);
    }

    /**
     * 参数缺失
     *
     * @return 系统标准响应对象
     */
    public static RespInfo paramterIsMissing() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.ParamterIsMissing);
    }

    /**
     * 应用ID缺失
     *
     * @return 系统标准响应对象
     */
    public static RespInfo appIdIsMissing() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.AppIdIsMissing);
    }

    /**
     * 权限不足
     *
     * @return 系统标准响应对象
     */
    public static RespInfo insufficientauthority() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.InsufficientAuthority);
    }

    /**
     * 权限不足
     *
     * @param msg 错误提示信息
     * @return 系统标准响应对象
     */
    public static RespInfo insufficientauthority(String msg) {
        return getRespInfoFromCode(msg, ServiceErrorCodeEnum.InsufficientAuthority);
    }


    /**
     * 权限检查错误
     *
     * @return 系统标准响应对象
     */
    public static RespInfo authError() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.AuthError);
    }


    /**
     * 服务已下架
     *
     * @return 系统标准响应对象
     */
    public static RespInfo serverIsDown() {
        return getRespInfoFromCode(ServiceErrorCodeEnum.ServerIsDown);
    }

    /**
     * 根据系统错误枚举构造响应对象
     * error和value字段均为空字符串
     *
     * @param errorCodeEnum 系统错误枚举
     * @return 系统标准响应对象
     */
    public static RespInfo getRespInfoFromCode(ServiceErrorCodeEnum errorCodeEnum) {
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(errorCodeEnum.getErrorCode());
        respInfo.setError(StringUtil.EMPTY);
        respInfo.setMsg(errorCodeEnum.getErrorStr());
        respInfo.setValue(StringUtil.EMPTY);
        return respInfo;
    }

    /**
     * 根据系统错误枚举和错误信息构造响应对象
     * error和value字段均为空字符串
     * msg字段使用形参中的msg进行构造
     *
     * @param msg           错误信息
     * @param errorCodeEnum 系统错误枚举
     * @return 系统标准响应对象
     */
    public static RespInfo getRespInfoFromCode(String msg, ServiceErrorCodeEnum errorCodeEnum) {
        RespInfo respInfo = getRespInfoFromCode(errorCodeEnum);
        respInfo.setMsg(msg);
        return respInfo;
    }


    /**
     * 根据系统错误枚举和错误信息构造响应对象
     * error和value字段均为空字符串
     * msg字段使用形参中的msg进行构造
     *
     * @param msg           错误信息
     * @param error         错误跟踪信息
     * @param errorCodeEnum 系统错误枚举
     * @return 系统标准响应对象
     */
    public static RespInfo getRespInfoFromCode(String msg, String error, ServiceErrorCodeEnum errorCodeEnum) {
        RespInfo respInfo = getRespInfoFromCode(errorCodeEnum);
        respInfo.setMsg(msg);
        respInfo.setError(error);
        return respInfo;
    }

    /**
     * 根据系统错误枚举和错误信息构造响应对象
     * error字段为空字符串
     * msg字段使用形参中的msg进行构造
     * value字段使用形参中valueData进行构造
     *
     * @param msg           错误信息
     * @param errorCodeEnum 系统错误枚举
     * @param valueData     响应值
     * @return 系统标准响应对象
     */
    public static RespInfo getRespInfoFromCode(String msg, ServiceErrorCodeEnum errorCodeEnum, Object valueData) {
        RespInfo respInfo = getRespInfoFromCode(msg, errorCodeEnum);
        respInfo.setValue(valueData);
        return respInfo;
    }

    /**
     * 根据系统错误枚举和错误信息构造响应对象
     * error字段为空字符串
     * value字段使用形参中valueData进行构造
     *
     * @param errorCodeEnum 系统错误枚举
     * @param valueData     响应值
     * @return 系统标准响应对象
     */
    public static RespInfo getRespInfoFromCode(ServiceErrorCodeEnum errorCodeEnum, Object valueData) {
        RespInfo respInfo = getRespInfoFromCode(errorCodeEnum);
        respInfo.setValue(valueData);
        return respInfo;
    }

    // endregion  RespInfo构造支持
//
//    // region 判定支持
//
//    /**
//     * 判定交互结果成功
//     *
//     * @param resp 交互结果
//     * @return true: 交互结果成功| false:交互结果不成功
//     */
//    public static boolean isRespSuccess(MicroObjectResp<?> resp) {
//        if (resp == null) {
//            return false;
//        }
//        return resp.getCode() == ServiceErrorCodeEnum.SUCCESS.getErrorCode();
//    }
//
//    /**
//     * 判定交互结果成功
//     *
//     * @param resp 交互结果
//     * @return true: 交互结果成功| false:交互结果不成功
//     */
//    public static boolean isRespSuccess(MicroArrayResp<?> resp) {
//        if (resp == null) {
//            return false;
//        }
//        return resp.getCode() == ServiceErrorCodeEnum.SUCCESS.getErrorCode();
//    }
//
//    /**
//     * 判定交互结果成功
//     *
//     * @param resp 交互结果
//     * @return true: 交互结果成功| false:交互结果不成功
//     */
//    public static boolean isRespSuccess(RespInfo resp) {
//        if (resp == null) {
//            return false;
//        }
//        return resp.getCode() == ServiceErrorCodeEnum.SUCCESS.getErrorCode();
//    }
//
//    // endregion 判定支持
//
//    // region MicroObjectResp构造
//
//    /**
//     * 构造MicroObjectResp成功交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param <T> 泛型支持
//     * @return MicroObjectResp成功交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getSuccessMicroObjectRespInfo(T t) {
//        return getSuccessMicroObjectRespInfo(t, null);
//    }
//
//    /**
//     * 构造MicroObjectResp成功交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param msg 成功提示信息
//     * @param <T> 泛型支持
//     * @return MicroObjectResp成功交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getSuccessMicroObjectRespInfo(T t, String msg) {
//        return getMicroObjectRespInfo(t, msg, ServiceErrorCodeEnum.SUCCESS, null);
//    }
//
//    /**
//     * 构造MicroObjectResp失败交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param <T> 泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getErrorMicroObjectRespInfo(T t) {
//        return getMicroObjectRespInfo(t, ServiceErrorCodeEnum.ERROR.getErrorStr(), ServiceErrorCodeEnum.ERROR, ServiceErrorCodeEnum.ERROR.getErrorStr());
//    }
//
//
//    /**
//     * 构造MicroObjectResp失败交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   错误提示信息
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getErrorMicroObjectRespInfo(T t, String msg, String error) {
//        return getMicroObjectRespInfo(t, msg, ServiceErrorCodeEnum.ERROR, error);
//    }
//
//    /**
//     * 构造MicroObjectResp失败交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   错误提示信息
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getErrorMicroObjectRespInfo(Class<T> t, String msg, String error) {
//        return getMicroObjectRespInfo(null, msg, ServiceErrorCodeEnum.ERROR, error);
//    }
//
//
//    /**
//     * 转换不同类型的MicroObjectResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> translateErrorMicroObjectRespInfo(Class<T> t, MicroObjectResp<?> errorResp) {
//        return getMicroObjectRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 转换不同类型的MicroObjectResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> translateErrorMicroObjectRespInfo(Class<T> t, RespInfo errorResp) {
//        return getMicroObjectRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 转换不同类型的MicroObjectResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroObjectResp<T> translateErrorMicroObjectRespInfo(Class<T> t, MicroArrayResp<?> errorResp) {
//        return getMicroObjectRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 构造MicroObjectResp交互结果对象
//     *
//     * @param t        交互结果对象
//     * @param msg      提示信息
//     * @param codeEnum 错误类型枚举
//     * @param error    详细错误信息
//     * @param <T>      泛型支持
//     * @return MicroObjectResp交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getMicroObjectRespInfo(T t, String msg, ServiceErrorCodeEnum codeEnum, String error) {
//        MicroObjectResp<T> tmpResult = new MicroObjectResp<>();
//        tmpResult.setCode(codeEnum.getErrorCode());
//        tmpResult.setMsg(StringUtil.isEmpty(msg) ? codeEnum.getErrorStr() : msg);
//        tmpResult.setError(error);
//        tmpResult.setValue(t);
//        return tmpResult;
//    }
//
//    /**
//     * 构造MicroObjectResp交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   提示信息
//     * @param code  错误码
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroObjectResp交互结果对象
//     */
//    public static <T> MicroObjectResp<T> getMicroObjectRespInfo(T t, String msg, int code, String error) {
//        MicroObjectResp<T> tmpResult = new MicroObjectResp<>();
//        tmpResult.setCode(code);
//        tmpResult.setMsg(msg);
//        tmpResult.setError(error);
//        tmpResult.setValue(t);
//        return tmpResult;
//    }
//
//
//    // endregion MicroObjectResp构造
//
//    // region MicroArrayResp构造
//
//    /**
//     * 构造MicroArrayResp成功交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param <T> 泛型支持
//     * @return MicroArrayResp成功交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getSuccessMicroArrayRespInfo(List<T> t) {
//        return getSuccessMicroArrayRespInfo(t, null);
//    }
//
//    /**
//     * 构造MicroArrayResp成功交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param msg 成功提示信息
//     * @param <T> 泛型支持
//     * @return MicroArrayResp成功交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getSuccessMicroArrayRespInfo(List<T> t, String msg) {
//        return getMicroArrayRespInfo(t, msg, ServiceErrorCodeEnum.SUCCESS, null);
//    }
//
//    /**
//     * 构造MMicroArrayResp失败交互结果对象
//     *
//     * @param t   交互结果对象
//     * @param <T> 泛型支持
//     * @return MicroArrayResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getErrorMicroArrayRespInfo(List<T> t) {
//        return getMicroArrayRespInfo(t, ServiceErrorCodeEnum.ERROR.getErrorStr(), ServiceErrorCodeEnum.ERROR, ServiceErrorCodeEnum.ERROR.getErrorStr());
//    }
//
//
//    /**
//     * 构造MicroArrayResp失败交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   错误提示信息
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroObjectResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getErrorMicroArrayRespInfo(List<T> t, String msg, String error) {
//        return getMicroArrayRespInfo(t, msg, ServiceErrorCodeEnum.ERROR, error);
//    }
//
//    /**
//     * 构造MMicroArrayResp失败交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   错误提示信息
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroArrayResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getErrorMicroArrayRespInfo(Class<T> t, String msg, String error) {
//        return getMicroArrayRespInfo(null, msg, ServiceErrorCodeEnum.ERROR, error);
//    }
//
//
//    /**
//     * 转换不同类型的MicroArrayResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroArrayResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> translateErrorMicroArrayRespInfo(Class<T> t, MicroObjectResp<?> errorResp) {
//        return getMicroArrayRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 转换不同类型的MicroArrayResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroArrayResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> translateErrorMicroArrayRespInfo(Class<T> t, MicroArrayResp<?> errorResp) {
//        return getMicroArrayRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 转换不同类型的MicroArrayResp失败交互结果对象
//     *
//     * @param t         交互结果对象
//     * @param errorResp 原错误交互结果对象
//     * @param <T>       泛型支持
//     * @return MicroArrayResp错误交互结果对象
//     */
//    public static <T> MicroArrayResp<T> translateErrorMicroArrayRespInfo(Class<T> t, RespInfo errorResp) {
//        return getMicroArrayRespInfo(null, errorResp.getMsg(), errorResp.getCode(), errorResp.getError());
//    }
//
//    /**
//     * 构造MicroArrayResp交互结果对象
//     *
//     * @param t        交互结果对象
//     * @param msg      提示信息
//     * @param codeEnum 错误类型枚举
//     * @param error    详细错误信息
//     * @param <T>      泛型支持
//     * @return MicroArrayResp交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getMicroArrayRespInfo(List<T> t, String msg, ServiceErrorCodeEnum codeEnum, String error) {
//        MicroArrayResp<T> tmpResult = new MicroArrayResp<>();
//        tmpResult.setCode(codeEnum.getErrorCode());
//        tmpResult.setMsg(StringUtil.isEmpty(msg) ? codeEnum.getErrorStr() : msg);
//        tmpResult.setError(error);
//        tmpResult.setValue(t);
//        return tmpResult;
//    }
//
//    /**
//     * 构造MicroArrayResp交互结果对象
//     *
//     * @param t     交互结果对象
//     * @param msg   提示信息
//     * @param code  错误码
//     * @param error 详细错误信息
//     * @param <T>   泛型支持
//     * @return MicroArrayResp交互结果对象
//     */
//    public static <T> MicroArrayResp<T> getMicroArrayRespInfo(List<T> t, String msg, int code, String error) {
//        MicroArrayResp<T> tmpResult = new MicroArrayResp<>();
//        tmpResult.setCode(code);
//        tmpResult.setMsg(msg);
//        tmpResult.setError(error);
//        tmpResult.setValue(t);
//        return tmpResult;
//    }
//
//
//    // endregion MicroArrayResp构造

}
