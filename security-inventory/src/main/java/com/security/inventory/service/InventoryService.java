package com.security.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.inventory.domain.ProductStockDO;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.domain.request.ReleaseProductStockRequest;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface InventoryService extends IService<ProductStockDO> {

    /**
     * 锁定商品库存
     * @param lockProductStockRequest
     * @return
     */
    Boolean lockProductStock(LockProductStockRequest lockProductStockRequest);

    /**
     * 释放商品库存
     */
    Boolean releaseProductStock(ReleaseProductStockRequest releaseProductStockRequest);

}
