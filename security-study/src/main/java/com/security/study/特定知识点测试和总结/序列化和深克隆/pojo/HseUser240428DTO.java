package com.security.study.特定知识点测试和总结.序列化和深克隆.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HseUser240428DTO extends HseBaseDTO implements Serializable {

    private String name;
    private Integer age;
    private BigDecimal jinqian;

    private HseUser240428DTO childrenDi;


    private List<HseUser240428DTO> childrenOther;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getJinqian() {
        return jinqian;
    }

    public void setJinqian(BigDecimal jinqian) {
        this.jinqian = jinqian;
    }

    public HseUser240428DTO getChildrenDi() {
        return childrenDi;
    }

    public void setChildrenDi(HseUser240428DTO childrenDi) {
        this.childrenDi = childrenDi;
    }

    public List<HseUser240428DTO> getChildrenOther() {
        return childrenOther;
    }

    public void setChildrenOther(List<HseUser240428DTO> childrenOther) {
        this.childrenOther = childrenOther;
    }
}
