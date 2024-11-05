package com.security.order.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.security.order.model.dto.OrderInfoDTO;
import com.security.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderJvmController {

    @Resource
    OrderService orderService;


    /**
     *
     */
    @PostMapping("/selectByPage")
    public IPage<OrderInfoDTO> selectByPage(@RequestBody OrderInfoDTO orderInfoDTO) {
        Page page = new Page(orderInfoDTO.getPageNum(), orderInfoDTO.getPageSize());
        IPage iPage = orderService.selectByPage(page, orderInfoDTO);
        return iPage;
    }


}
