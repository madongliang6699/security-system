package com.security.order.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建订单返回结果
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
public class CreateOrderResponse implements Serializable {

    /**
     * 订单ID
     */
    private String orderId;

    // 库存不足的商品列表
}