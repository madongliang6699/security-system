package com.security.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.inventory.model.entity.ProductStockDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper extends BaseMapper<ProductStockDO> {



}
