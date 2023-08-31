package com.security.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.inventory.domain.ProductStockDO;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.domain.request.ReleaseProductStockRequest;
import com.security.inventory.mapper.InventoryMapper;
import com.security.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;


    /**
     * 锁定商品库存
     * @param lockProductStockRequest
     * @return
     */
    public Boolean lockProductStock(LockProductStockRequest lockProductStockRequest){

        List<LockProductStockRequest.OrderItemRequest> orderItemRequestList = lockProductStockRequest.getOrderItemRequestList();

        for (LockProductStockRequest.OrderItemRequest orderItemRequest : orderItemRequestList) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("sku_code", orderItemRequest.getSkuCode());
            ProductStockDO productStockDO = inventoryMapper.selectOne(queryWrapper);





        }





        return true;
    }

    /**
     * 释放商品库存
     */
    public Boolean releaseProductStock(ReleaseProductStockRequest releaseProductStockRequest){
        return null;

    }

}
