package com.security.order.service;

import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;
import com.security.order.domain.response.CreateOrderResponse;
import com.security.order.domain.response.GenOrderIdResponse;
import com.security.order.model.dto.OrderInfoDTO;

import java.util.List;

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

    /**
     * 下订单
     */
    String placeOrder(OrderInfoDTO orderInfoDTO);

    /**
     * 查询所有订单
     */
    List<OrderInfoDTO> selectAllOrder();
}
