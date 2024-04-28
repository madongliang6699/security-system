package com.security.study.特定知识点测试和总结.序列化和深克隆.pojo;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseDTO implements Serializable {

    private Long sid;
    private Integer appId;
    private String keyword;
    private Date startDate;
    private Date endDate;


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "sid=" + sid +
                ", appId=" + appId +
                ", keyword='" + keyword + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
