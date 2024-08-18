package com.security.inventory.api.impl;

import com.security.common.core.JsonResult;
import com.security.inventory.api.InventoryTestTraceIdApi;
import com.security.inventory.service.InventoryTestTraceIdService;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@Log4j2
@DubboService(version = "1.0.0", interfaceClass = InventoryTestTraceIdApi.class, retries = 0)
public class InventoryTestTraceIdApiImpl implements InventoryTestTraceIdApi {

    @Resource
    InventoryTestTraceIdService inventoryTestTraceIdService;

    @Override
    public JsonResult<String> test1(String name) {
        String s = inventoryTestTraceIdService.test1(name);
        log.error("-----InventoryTestTraceIdApiImpl-----返回数据：{}", s);
        log.info("-----InventoryTestTraceIdApiImpl-----返回数据：{}", s);
        return JsonResult.buildSuccess(s);
    }
}
