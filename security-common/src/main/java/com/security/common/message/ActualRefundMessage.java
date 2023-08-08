package com.security.common.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author madongliang
 * @version 1.0
 */
@Data
public class ActualRefundMessage implements Serializable {
    /**
     * 售后支付单id
     */
    private Long afterSaleRefundId;
    /**
     * 售后id
     */
    private Long afterSaleId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 当前订单是否是退最后一笔
     */
    private boolean lastReturnGoods = false;
}
