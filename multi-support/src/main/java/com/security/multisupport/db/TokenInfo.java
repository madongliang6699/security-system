package com.security.multisupport.db;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * token信息实体类
 *
 * @Author zeng
 * @DateTime 2020-06-12 09:51
 * @FileName TokenInfo.java
 */
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // region 构造

    public TokenInfo() {
    }

    public TokenInfo(Integer appId, String token) {
        this.appId = appId;
        this.token = token;
    }

    public TokenInfo(Integer appId, Long userId, String userCode, String userName, Date loginDate) {
        this.appId = appId;
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.loginDate = loginDate;
    }

    public TokenInfo(Integer appId, Long userId, String userCode, String userName, Date loginDate, String token) {
        this.appId = appId;
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.loginDate = loginDate;
        this.token = token;
    }

    public TokenInfo(Integer appId, Long userId, String userCode, String userName, Date loginDate, List<Long> roles) {
        this.appId = appId;
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.loginDate = loginDate;
        this.roles = roles;
    }

    // endregion 构造


    // region 属性定义

    /**
     * appId
     */
    private Integer appId;

    /**
     * 用户账户流水ID
     */
    private Long userId;

    /**
     * 用户账户登陆名
     */
    private String userCode;

    /**
     * 用户账户名称
     */
    private String userName;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 登陆时间
     */
    private Date loginDate;

    /**
     * 过期时间
     */
    private Date expTime;

    /**
     * token
     */
    private String token;

    /**
     * Http请求头中AppId值
     */
    private Integer headerAppId;

    /**
     * 角色列表
     */
    private List<Long> roles;

    /**
     * 分层ID
     */
    private String tenantId;

    /**
     * 分层名称
     */
    private String tenantName;

    /**
     * 客户端标识
     */
    private String clientFlag;

    // endregion 属性定义


    // region getter and setter

    /**
     * 获取appid
     *
     * @return appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置appid
     *
     * @param appId appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取用户账户流水ID
     *
     * @return 用户账户流水ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户账户流水ID
     *
     * @param userId 用户账户流水ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户账户登陆名
     *
     * @return 用户账户登陆名
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户账户登陆名
     *
     * @param userCode 用户账户登陆名
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取用户账户名称
     *
     * @return 用户账户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户账户名称
     *
     * @param userName 用户账户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户类型
     *
     * @return 用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取登陆时间
     *
     * @return 登陆时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 设置登陆时间
     *
     * @param loginDate 登陆时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    public Date getExpTime() {
        return expTime;
    }

    /**
     * 设置过期时间
     *
     * @param expTime 过期时间
     */
    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    /**
     * 获取token字符串
     *
     * @return token字符串
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token字符串
     *
     * @param token token字符串
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取Http请求头中appid值
     *
     * @return Http请求头中appid值
     */
    public Integer getHeaderAppId() {
        return headerAppId;
    }

    /**
     * 设置Http请求头中appid值
     *
     * @param headerAppId Http请求头中appid值
     */
    public void setHeaderAppId(Integer headerAppId) {
        this.headerAppId = headerAppId;
    }


    /**
     * 获取角色列表
     *
     * @return 角色列表
     */
    public List<Long> getRoles() {
        return roles;
    }

    /**
     * 设置角色列表
     *
     * @param roles 角色列表
     */
    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    /**
     * 获取分层ID
     *
     * @return 分层ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置分层ID
     *
     * @param tenantId 分层ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取分层名称
     *
     * @return 分层名称
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * 设置分层名称
     *
     * @param tenantName 分层名称
     */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    /**
     * 获取客户端标识
     *
     * @return 客户端标识
     */
    public String getClientFlag() {
        return clientFlag;
    }

    /**
     * 设置客户端标识
     *
     * @param clientFlag 客户端标识
     */
    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }


    // endregion getter and setter


    /**
     * 获取Token计算的Map对象
     *
     * @return Token计算的Map对象
     */
    public Map<String, Object> getFieldMap() {
        Map<String, Object> tmpFieldMap = new HashMap<>(9);
        if (appId != null) {
            tmpFieldMap.put("appId", appId);
        }
        if (headerAppId != null) {
            tmpFieldMap.put("headerAppId", headerAppId);
        }

        if (userId != null) {
            tmpFieldMap.put("userId", userId);
        }

        if (!StringUtils.isEmpty(userCode)) {
            tmpFieldMap.put("userCode", userCode);
        } else {
            tmpFieldMap.put("userCode", "");
        }

        if (!StringUtils.isEmpty(userName)) {
            tmpFieldMap.put("userName", userName);
        } else {
            tmpFieldMap.put("userName", "");
        }

        if (!StringUtils.isEmpty(userType)) {
            tmpFieldMap.put("userType", userType);
        } else {
            tmpFieldMap.put("userType", "");
        }

        if (loginDate != null) {
            tmpFieldMap.put("loginDate", loginDate);
        }
        if (expTime != null) {
            tmpFieldMap.put("expTime", expTime);
        }
        if (roles != null) {
            tmpFieldMap.put("roles", roles);
        }
        if (!StringUtils.isEmpty(tenantId)) {
            tmpFieldMap.put("tenantId", tenantId);
        } else {
            tmpFieldMap.put("tenantId", "");
        }
        if (!StringUtils.isEmpty(tenantName)) {
            tmpFieldMap.put("tenantName", tenantName);
        } else {
            tmpFieldMap.put("tenantName", "");
        }
        if (!StringUtils.isEmpty(clientFlag)) {
            tmpFieldMap.put("clientFlag", clientFlag);
        } else {
            tmpFieldMap.put("clientFlag", "");
        }
        return tmpFieldMap;
    }

    // region 扩展支持

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public Date getCurrentDate(){
        return new Date();
    }


    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public LocalDate getCurrentLocalDate(){
        return LocalDate.now();
    }


    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public LocalDateTime getCurrentLocalDateTime(){
        return LocalDateTime.now();
    }

    // endregion 扩展支持
}
