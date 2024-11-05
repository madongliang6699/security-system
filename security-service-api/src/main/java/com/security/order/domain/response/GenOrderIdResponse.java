package com.security.order.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
public class GenOrderIdResponse implements Serializable {

    /**
     * 订单号
     */
    private String orderId;

}