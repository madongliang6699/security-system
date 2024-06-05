package com.security.order.controller;


import cn.hutool.core.date.StopWatch;
import com.security.order.model.dto.OrderInfoDTO;
import com.security.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    public AtomicLong atomicLong = new AtomicLong();

    /**
     * 下订单
     */
    @PostMapping("/placeOrder")
    public String placeOrder(@Valid @RequestBody OrderInfoDTO orderInfoDTO) {
        String orderId = orderService.placeOrder(orderInfoDTO);

        System.out.println("atomicLong="+atomicLong.incrementAndGet());
        return orderId;
    }

    /**
     * 下订单
     */
    @GetMapping("/selectAllOrder")
    public List<OrderInfoDTO> selectAllOrder() {
        List<OrderInfoDTO> orderInfoDTOS = orderService.selectAllOrder();
        return orderInfoDTOS;
    }


}
