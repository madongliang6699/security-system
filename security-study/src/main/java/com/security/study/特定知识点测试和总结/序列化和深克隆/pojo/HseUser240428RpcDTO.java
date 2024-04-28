package com.security.study.特定知识点测试和总结.序列化和深克隆.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HseUser240428RpcDTO extends BaseDTO implements Serializable {

    private String name;
    private String age;
    private BigDecimal jinqian;

    private HseUser240428RpcDTO childrenDi;

    private List<HseUser240428RpcDTO> childrenOther;


}
