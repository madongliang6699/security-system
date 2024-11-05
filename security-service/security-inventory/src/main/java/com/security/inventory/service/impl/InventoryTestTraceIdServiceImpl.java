package com.security.inventory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.security.inventory.service.InventoryTestTraceIdService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class InventoryTestTraceIdServiceImpl implements InventoryTestTraceIdService {
    @Override
    public String test1(String name) {
        log.info("-----InventoryTestTraceIdServiceImpl-----你好：{}", name);
        log.error("-----InventoryTestTraceIdServiceImpl-----你好：{}", name);
        return StrUtil.format("你好: {}", name);
    }
}
