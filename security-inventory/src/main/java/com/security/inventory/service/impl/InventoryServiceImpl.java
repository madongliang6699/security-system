package com.security.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.inventory.domain.ProductStockDO;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.domain.request.ReleaseProductStockRequest;
import com.security.inventory.mapper.InventoryMapper;
import com.security.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, ProductStockDO> implements InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;


    /**
     * 锁定商品库存
     *
     * @param lockProductStockRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class) //todo 这里注释掉，好像全局事务也能回滚本地的事务，使本地方法内的多个修改也能做到数据一致性
    public Boolean lockProductStock(LockProductStockRequest lockProductStockRequest) {
        /*
         这里固定使用id为1和2的数据，模拟当前订单中买了两个商品，分别是两种sku的具体商品，锁定这两个sku的库存。
         */

        ProductStockDO byId = getById(1);
        byId.setSaleStockQuantity(byId.getSaleStockQuantity() - 1);
        byId.setLockedStockQuantity(byId.getLockedStockQuantity() + 1);
        updateById(byId);

        /*
          todo 这里先测试本地事务是否可用，然后测试seata全局事务是否可用。
         */
//        int i = 9 / 0;

        ProductStockDO byId2 = getById(2);
        byId2.setSaleStockQuantity(byId2.getSaleStockQuantity() - 1);
        byId2.setLockedStockQuantity(byId2.getLockedStockQuantity() + 1);
        updateById(byId2);

        return true;
    }

    /**
     * 释放商品库存
     */
    public Boolean releaseProductStock(ReleaseProductStockRequest releaseProductStockRequest) {
        return null;

    }

}
