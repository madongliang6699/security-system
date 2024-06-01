package com.security.market.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.security.common.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 运费模板
 * </p>
 *
 * @author zhonghuashishan
 */
@Data
@TableName("market_yunfei")
public class FreightTemplateDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区域ID
     */
    private String areaName;

    /**
     * 标准运费
     */
    private Integer yunfei;

}
