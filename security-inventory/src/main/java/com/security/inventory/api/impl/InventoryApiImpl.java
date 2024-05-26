package com.security.inventory.api.impl;

import com.security.common.core.JsonResult;
import com.security.inventory.api.InventoryApi;
import com.security.inventory.domain.request.CancelOrderReleaseProductStockRequest;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.exception.InventoryBizException;
import com.security.inventory.service.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService(version = "1.0.0", interfaceClass = InventoryApi.class, retries = 0)
public class InventoryApiImpl implements InventoryApi {

    @Resource
    InventoryService inventoryService;


    @Override
    public JsonResult<Boolean> lockProductStock(LockProductStockRequest lockProductStockRequest) {
        try {

            Boolean b = inventoryService.lockProductStock(lockProductStockRequest);

            return JsonResult.buildSuccess(b);
        } catch (InventoryBizException ex) {
            return JsonResult.buildError(ex.getErrorCode(), ex.getErrorMsg());
        } catch (Exception ex) {
            JsonResult objectJsonResult = JsonResult.buildError(ex.getMessage());
            return objectJsonResult;
        }
    }

    @Override
    public JsonResult<Boolean> cancelOrderReleaseProductStock(CancelOrderReleaseProductStockRequest cancelOrderReleaseProductStockRequest) {
        return null;
    }
}
