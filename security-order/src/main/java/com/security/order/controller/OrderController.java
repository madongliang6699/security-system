package com.security.order.controller;


import com.security.order.model.dto.OrderInfoDTO;
import com.security.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    /**
     * 下订单
     */
    public String placeOrder(OrderInfoDTO orderInfoDTO) {
        String orderId = orderService.placeOrder(orderInfoDTO);
        return orderId;
    }


}
