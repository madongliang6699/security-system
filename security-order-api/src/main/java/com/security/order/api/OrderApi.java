package com.security.order.api;

import com.security.common.core.JsonResult;
import com.security.order.domain.dto.CreateOrderDTO;
import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;

public interface OrderApi {
    
    /**
     * 生成订单号
     */
    JsonResult<GenOrderIdDTO> genOrderNo(GenOrderIdRequest genOrderIdRequest);
    
    /**
     * 提交订单接口
     *
     * @param createOrderRequest 提交订单请求入参
     * @return 订单号
     */
    JsonResult<CreateOrderDTO> createOrder(CreateOrderRequest createOrderRequest);


}
