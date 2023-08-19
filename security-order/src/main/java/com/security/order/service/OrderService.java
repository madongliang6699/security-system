package com.security.order.service;

import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.GenOrderIdRequest;

public interface OrderService {
    
    
    /**
     * 生成订单号
     *
     * @param genOrderIdRequest 生成订单号入参
     * @return 订单号
     */
    GenOrderIdDTO genOrderId(GenOrderIdRequest genOrderIdRequest);




}
