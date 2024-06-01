package com.security.order.api;

import com.security.common.core.JsonResult;
import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;
import com.security.order.domain.response.CreateOrderResponse;
import com.security.order.domain.response.GenOrderIdResponse;

public interface OrderApi {

    /**
     * 生成订单号
     */
    JsonResult<GenOrderIdResponse> genOrderNo(GenOrderIdRequest genOrderIdRequest);

    /**
     * 提交订单接口
     *
     * @param createOrderRequest 提交订单请求入参
     * @return 订单号
     */
    JsonResult<CreateOrderResponse> createOrder(CreateOrderRequest createOrderRequest);


    /**
     * 测试zk挂了
     */
    JsonResult<String> ceshi1(String aa);


}
