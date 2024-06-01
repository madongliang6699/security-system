package com.security.market.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.market.mapper.CouponMapper;
import com.security.market.model.entity.YouhuiquanEntity;
import com.security.market.other.enums.CouponUsedStatusEnum;
import com.security.market.other.exception.MarketBizException;
import com.security.market.other.exception.MarketErrorCodeEnum;
import com.security.market.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * 营销管理service组件
 *
 * @author zhonghuashishan
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, YouhuiquanEntity> implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean lockUserCoupon(String userId) {

        QueryWrapper<YouhuiquanEntity> tWrapper = new QueryWrapper<>();
        tWrapper.eq("user_id", userId);
        YouhuiquanEntity couponDO = couponMapper.selectOne(tWrapper);

        if (couponDO == null) {
            throw new MarketBizException(MarketErrorCodeEnum.USER_COUPON_IS_NULL);
        }

        if (Objects.equals(couponDO.getUsedTag(), CouponUsedStatusEnum.USED.getCode())) {
            throw new MarketBizException(MarketErrorCodeEnum.USER_COUPON_IS_USED);
        }

        couponDO.setUsedTag(CouponUsedStatusEnum.USED.getCode());
        couponDO.setUsedTime(new Date());
        couponMapper.updateById(couponDO);
        return true;
    }
}
