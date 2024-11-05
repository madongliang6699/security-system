package com.security.inventory.api;


import com.security.common.core.JsonResult;
import com.security.inventory.domain.request.LockProductStockRequest;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface InventoryTestTraceIdApi {

    /**
     * 锁定商品库存
     */
    JsonResult<String> test1(String name);


}