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
import org.springframework.web.bind.annotation.PathVariable;

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
         *
         *  知识点补充:select ... for update 在 MySQL 中通常是行锁，锁定查询条件选中的行，以防止其他事务在同一行上进行并发的 UPDATE 或 DELETE 操作。
         *  在某些情况下，如全表扫描或未使用索引(查询条件没有使用索引导致需要全表扫描的情况)，可能会升级为表锁。
         *  另外使用 select ... for update 时, 和后面的update操作一定要在同一个事务中,否则起不到for update的作用。
         *
         *  使用 for update 语句进行行锁定时，如果多个事务以不正确的顺序获取锁，可能会导致死锁。比如事务1先锁定了a这条数据,然后又用for update去获取b数据, 而事务2和事务1相反,先锁定b,又去获取a,就可能导致死锁.
         * MySQL 的 InnoDB 存储引擎可以自动检测到死锁，检测到死锁之后会立马通过回滚其中一个事务来解决死锁。被回滚的事务会收到一个错误消息，类似于：ERROR 1213 (40001): Deadlock found when trying to get lock; try restarting transaction.
         * 另外,如果msyql InnoDB 存储引擎有默认的事务等待锁超时的机制(通过innodb_lock_wait_timeout参数设置超时时间,默认50秒),如果事务A要获取某行数据的锁, 但是这行数据已经被事务A锁定, 而这两个事务又不是死锁关系,只是单纯的等待关系,
         * 那事务A就会一直等待事务B释放锁,但是超过innodb_lock_wait_timeout参数时间后,会返回:Lock wait timeout exceeded; try restarting transaction的错误.
         *
         *
         * 避免死锁的方法:
         *      一致的锁定顺序,
         *      缩短事务执行时间以降低发生死锁的概率,
         *      合理使用索引以减少锁定的行数,
         *      批量处理:将多个小的事务合并成一个批量处理操作以减少并发事务的数量,
         *      适当使用锁机制:如果可能，使用表锁而不是行锁，或者反过来，具体取决于你的应用场景和并发模式。
         *
         *
         * 死锁是在数据库并发处理中常见的问题，通过合理的锁定顺序、优化事务设计和使用适当的锁机制，可以有效地预防和解决死锁问题。
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

    @Override
    @Transactional
    public void testDeadlock(Boolean order) throws InterruptedException {

        if(order){
            inventoryMapper.selectStockForUpdate(1L);
            Thread.sleep(5000);
            inventoryMapper.selectStockForUpdate(2L);
        } else {
            inventoryMapper.selectStockForUpdate(2L);
            Thread.sleep(5000);
            inventoryMapper.selectStockForUpdate(1L);
        }


    }


}
