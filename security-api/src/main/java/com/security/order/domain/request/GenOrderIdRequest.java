package com.security.order.domain.request;

import java.io.Serializable;


public class GenOrderIdRequest implements Serializable {
    
    private static final long serialVersionUID = -3918194989507931383L;
    
    
    /**
     * 业务线标识
     * 目前仅仅支持一种业务标识，就是自营商城，B2C，大B端，直接对c端出售商品
     */
    private Integer businessIdentifier;
    
    /**
     * 用户ID
     */
    private String userId;
    
    
    public Integer getBusinessIdentifier() {
        return businessIdentifier;
    }
    
    public void setBusinessIdentifier(Integer businessIdentifier) {
        this.businessIdentifier = businessIdentifier;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
