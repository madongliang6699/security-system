package com.security.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.market.entity.CouponDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户优惠券记录表 Mapper 接口
 * </p>
 *
 * @author zhonghuashishan
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponDO> {

}
