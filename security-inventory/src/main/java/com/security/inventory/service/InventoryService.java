package com.security.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.model.entity.ProductStockDO;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface InventoryService extends IService<ProductStockDO> {

    /**
     * 锁定商品库存
     */
    Boolean lockProductStock(LockProductStockRequest lockProductStockRequest);

    void testDeadlock(Boolean order) throws InterruptedException;

}
