package com.security.market.model.dto;

import com.security.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户优惠券记录表
 * </p>
 *
 * @author zhonghuashishan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class YouhuiquanDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 是否使用过这个优惠券，1：使用了，0：未使用
     */
//    @TableField(value = "is_used")
    private Integer usedTag;

    /**
     * 使用优惠券的时间
     */
    private Date usedTime;

    /**
     * 抵扣金额
     */
    private Integer amount;
}
