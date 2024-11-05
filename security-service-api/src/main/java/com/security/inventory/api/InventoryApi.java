package com.security.inventory.api;


import com.security.common.core.JsonResult;
import com.security.inventory.domain.request.LockProductStockRequest;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface InventoryApi {

    /**
     * 锁定商品库存
     */
    JsonResult<Boolean> lockProductStock(LockProductStockRequest lockProductStockRequest);


}