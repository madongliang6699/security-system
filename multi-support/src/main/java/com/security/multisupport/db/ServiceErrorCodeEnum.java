package com.security.multisupport.db;

/**
 * 系统返回错误枚举类
 *
 * @author zeng
 * @File ServiceErrorCodeEnum.java
 * @Date 2018-12-06
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public enum ServiceErrorCodeEnum {

    /**
     * 执行成功
     */
    SUCCESS("执行成功", 0),

    /**
     * 执行错误
     */
    ERROR("执行错误", -1),


    /*===================系统错误信息===================*/
    /**
     * 系统内部错误
     */
    SysError("系统内部错误", 1000),

    /**
     * 跨模块调用出错
     */
    ModelInvokeError("跨模块调用出错", 1001),





    /*===================Token===================*/
    /**
     * Token未传
     */
    TokenIsMissing("Token未传", 1101),

    /**
     * 令牌被重置
     */
    TokenIsReset("令牌被重置,请重新登录", 1102),

    /**
     * 令牌过期,请重新登录
     */
    TokenIsOverdue("令牌过期,请重新登录", 1103),

    /**
     * 令牌解析失败
     */
    TokenParseError("令牌解析失败",1104),

    /**
     * 验证码错误
     */
    VerificationCodeError("验证码错误",1105),



    /*===================入参===================*/
    /**
     * 入参格式化错误
     */
    RequestFormatError("入参格式化错误", 1201),

    /**
     * 入参格式化错误
     */
    RequestIsNull("入参为空", 1102),

    /**
     * appid丢失
     */
    AppIdIsMissing("appid丢失", 1203),

    /**
     * 参数丢失
     */
    ParamterIsMissing("参数丢失", 1204),

    /*===================权限===================*/
    /**
     * 权限不足无法访问
     */
    CanNotAccess("权限不足无法访问", 1301),

    /**
     * 权限检查错误
     */
    AuthError("权限检查错误", 1302),

    /**
     * 权限检查失败
     */
    AuthFail("权限检查失败", 1303),



    /*===================服务状态===================*/
    /**
     * 服务无法请求
     */
    ServerIsDown("服务无法请求", 1401),

    /**
     * 服务未找到
     */
    ServersIsNotFound("服务未找到", 1402),



    /*===================业务失败===================*/

    /*=========数据库=========*/
    /**
     * 缺少主键
     */
    PrimaryKeyIsMissing("缺少主键", 15101),



    /*=========RPC=========*/
    /**
     * 连接超时错误
     */

    Connect_Time_Error("连接超时错误", 15201),

    /**
     * 响应超时错误
     */
    Respones_Time_Error("响应超时错误", 15202),

    /**
     * 未知地址错误
     */
    UnknownHost_Error("未知地址错误", 15203),
    /**
     * 未知地址错误
     */
    Connection_Error("请求连接错误", 15204),

    /**
     * Http请求IO错误
     */
    Http_Req_IoError("Http请求IO错误", 15205),

    /**
     * 无法连接到Zookeeper
     */
    Zookeeper_Can_Not_Connect_Error("无法连接到Zookeeper", 15206),



    /*=========自身业务功能=========*/

    /*=========中间件=========*/

    /*=========业务控制器执行失败=========*/
    /**
     * Http请求方法名错误
     */
    MethodNameError("Http请求方法名错误", 15507),

    /**
     * 参数格式化错误
     */
    ParamterFormatError("参数格式化错误", 15508),

    /**
     * Json反序列化错误
     */
    JsonFormatError("Json反序列化错误", 15509),



    /*=========登陆=========*/
    /**
     * 用户名或密码错误
     */
    UserNameOrPasswordError("用户名或密码错误", 15601),

    /**
     * 手机号码错误
     */
    PhoneNumberError("手机号码错误", 15602),

    /**
     * 账户未找到
     */
    UserNotFoundError("账户未找到", 15603),

    /**
     * 账户名或密码为空
     */
    UserNameOrPasswordIsEmpty("账户名或密码为空", 15604),

    /**
     * 账户锁定,请联系管理员解锁
     */
    UserStatusError("账户锁定,请联系管理员解锁", 15605),

    /**
     * 权限不足
     */
    InsufficientAuthority("权限不足", 15607),



    /*=========终端=========*/

    /*=========工厂模型=========*/

    /*=========用户=========*/
    /**
     * 该账户已被注册
     */
    AccountNoIsRegistered("该账户已被注册", 16101),


    /**
     * 原密码错误
     */
    OldPasswordError("原密码错误", 16102),





    Normal("", 50000);

    /**
     * 错误代码注释
     */
    private String errorStr;

    /**
     * 错误代码值
     */
    private int errorCode;

    /**
     * 错误代码枚举构造函数
     *
     * @param errorStr  错误代码注释
     * @param errorCode 错误代码值
     */
    ServiceErrorCodeEnum(String errorStr, int errorCode) {
        this.errorStr = errorStr;
        this.errorCode = errorCode;
    }

    /**
     * 获取错误代码注释
     *
     * @return 错误代码注释
     */
    public String getErrorStr() {
        return errorStr;
    }

    /**
     * 设置错误代码注释
     *
     * @param errorStr 错误代码注释
     */
    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }

    /**
     * 获取错误代码值
     *
     * @return 错误代码值
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 设置错误代码值
     *
     * @param errorCode 错误代码值
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * 构造自定义错误信息
     *
     * @param errorStr  错误信息字符串
     * @param errorCode 错误码(大于10000)
     * @return 自定义错误信息
     */
    public static ServiceErrorCodeEnum makeCustomerError(String errorStr, int errorCode) {

        ServiceErrorCodeEnum serviceErrorCodeEnum = Normal;
        if (errorCode > Normal.getErrorCode()) {
            serviceErrorCodeEnum.setErrorCode(errorCode);
        }
        serviceErrorCodeEnum.setErrorStr(errorStr);
        return serviceErrorCodeEnum;
    }
}
