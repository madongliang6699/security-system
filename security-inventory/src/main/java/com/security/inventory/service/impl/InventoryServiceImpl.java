package com.security.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.inventory.mapper.InventoryMapper;
import com.security.inventory.model.entity.ProductStockDO;
import com.security.inventory.other.exception.InventoryBizException;
import com.security.inventory.other.exception.InventoryErrorCodeEnum;
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

        //todo 下面这里的先获取数据,然后修改记录并保存的操作是有并发安全问题的, 因为当多个线程同时执行了getById方法,获取的数据是一样的, 然后又同时在一样数据的基础上执行下面的updateById数据到数据库, 导致数据错误.
        //ProductStockDO productStockDO = this.getById(lockProductStockRequest.getProductId());


        /**
         * todo 改进的方式有很多:
         *  第一种:查询的时候使用select for update的sql. (要小心避免死锁)
         *  第二种:直接一个sql完成修改操作, 不过这种方式不好判断库存是不是不足, 也需要sql判断.
         *  第三种:使用锁, 把查询和修改的过程放在一个原子锁里.
         */
        ProductStockDO productStockDO = inventoryMapper.selectStockForUpdate(lockProductStockRequest.getProductId());
        if(productStockDO.getSaleStockQuantity() <= lockProductStockRequest.getQuantity()){
            throw new InventoryBizException(InventoryErrorCodeEnum.PRODUCT_SKU_STOCK_Insufficient);
        }

        productStockDO.setSaleStockQuantity(productStockDO.getSaleStockQuantity() - lockProductStockRequest.getQuantity());
        productStockDO.setLockedStockQuantity(productStockDO.getLockedStockQuantity() + lockProductStockRequest.getQuantity());
        this.updateById(productStockDO);

        return true;
    }

}
