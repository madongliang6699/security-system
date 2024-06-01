package com.security.order.model.dto;

import com.security.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单明细表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemDTO extends BaseEntity {

    //订单编号
    private String orderId;
    //订单明细编号
    private String orderItemId;
    //商品编号
    private String productId;
    //商品名称
    private String productName;
    //销售数量
    private Integer saleQuantity;
    //销售单价
    private Double salePrice;
    //交易⽀付⾦额
    private BigDecimal payAmount;

}
