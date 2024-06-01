package com.security.order.service;

import com.security.order.domain.response.CreateOrderResponse;
import com.security.order.domain.response.GenOrderIdResponse;
import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;

public interface OrderService {
    
    
    /**
     * 生成订单号
     *
     * @param genOrderIdRequest 生成订单号入参
     * @return 订单号
     */
    GenOrderIdResponse genOrderId(GenOrderIdRequest genOrderIdRequest);
    
    
    /**
     * 提交订单/生成订单
     *
     * @param createOrderRequest 提交订单请求入参
     * @return 订单号
     */
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

}
