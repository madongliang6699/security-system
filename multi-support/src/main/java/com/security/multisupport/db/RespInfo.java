package com.security.multisupport.db;

import java.io.Serializable;

/**
 * 系统返回数据基础格式
 *
 * @author zeng
 * @File RespInfo.java
 * @Date 2018-12-06
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class RespInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 报错信息
     */
    String error;
    /**
     * 执行信息
     */
    String msg;
    /**
     * 错误代码
     */
    int code;
    /**
     * 返回对象
     */
    Object value;


    /**
     * 构造函数
     */
    public RespInfo() {
    }

    /**
     * 构造函数
     *
     * @param error 报错信息
     * @param msg   执行信息
     * @param code  错误代码
     * @param value 返回对象
     */
    public RespInfo(String error, String msg, int code, Object value) {
        this.error = error;
        this.msg = msg;
        this.code = code;
        this.value = value;
    }

    /**
     * 获取报错信息
     *
     * @return 报错信息
     */
    public String getError() {
        return error;
    }

    /**
     * 设置报错信息
     *
     * @param error 报错信息
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 获取执行信息
     *
     * @return 执行信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置执行信息
     *
     * @param msg 执行信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置错误代码
     *
     * @param code 错误代码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取返回对象
     *
     * @return 返回对象
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置返回对象
     *
     * @param value 返回对象
     */
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RespInfo{" +
                "error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", value=" + value +
                '}';
    }
}
