package com.security.inventory.controller;


import com.security.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    InventoryService inventoryService;


    /**
     * 测试 for update 死锁
     */
    @PostMapping("/testDeadlock")
    public void testDeadlock(Boolean order) throws InterruptedException {
        inventoryService.testDeadlock(order);
    }

}
