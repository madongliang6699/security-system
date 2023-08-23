package com.security.market.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.common.dao.BaseDAO;
import com.security.market.entity.CouponConfigDO;
import com.security.market.mapper.CouponConfigMapper;
import org.springframework.stereotype.Repository;

/**
 * 优惠券管理DAO组件
 *
 * @author zhonghuashishan
 */
@Repository
public class CouponConfigDAO extends BaseDAO<CouponConfigMapper, CouponConfigDO> {

    /**
     * 优惠券配置信息
     * @param couponConfigId
     * @return
     */
    public CouponConfigDO getByCouponConfigId(String couponConfigId) {
        QueryWrapper<CouponConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_config_id", couponConfigId);
        return getOne(queryWrapper);
    }

}
