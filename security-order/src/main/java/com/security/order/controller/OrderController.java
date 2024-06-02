package com.security.order.controller;


import cn.hutool.core.date.StopWatch;
import com.security.order.model.dto.OrderInfoDTO;
import com.security.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    /**
     * 下订单
     */
    @PostMapping("/placeOrder")
    public String placeOrder(@Valid @RequestBody OrderInfoDTO orderInfoDTO) {
        StopWatch stopWatch = new StopWatch("测试时间");
        stopWatch.start("controller时间");
        String orderId = orderService.placeOrder(orderInfoDTO);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
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
