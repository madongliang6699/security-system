package com.security.inventory.domain.request;

import com.security.common.core.AbstractObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 锁定商品库存入参
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
public class LockProductStockRequest extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 8229493558996271243L;

    //商品id
    private Long productId;
    //销售数量（需要锁的库存数量）
    private Integer quantity;

}