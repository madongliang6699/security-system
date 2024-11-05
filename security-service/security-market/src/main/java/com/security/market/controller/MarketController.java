package com.security.market.controller;

import com.security.common.core.JsonResult;
import com.security.order.api.OrderApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {


    @DubboReference(version = "1.0.0", retries = 0, timeout = 10000)
    OrderApi orderApi;


    /**
     * 测试
     */
    @RequestMapping("/ceshi1")
    public JsonResult<String> ceshi1() {
        return orderApi.ceshi1("d");
    }


}
