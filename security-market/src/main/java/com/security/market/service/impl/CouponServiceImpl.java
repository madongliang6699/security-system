package com.security.market.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.market.constants.MarketConstant;
import com.security.market.domain.request.CalculateOrderAmountRequest;
import com.security.market.entity.CouponDO;
import com.security.market.entity.FreightTemplateDO;
import com.security.market.enums.CouponUsedStatusEnum;
import com.security.market.exception.MarketBizException;
import com.security.market.exception.MarketErrorCodeEnum;
import com.security.market.mapper.CouponMapper;
import com.security.market.mapper.FreightTemplateMapper;
import com.security.market.service.CouponService;
import com.security.market.service.MarketService;
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
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean lockUserCoupon(String userId) {

        QueryWrapper<CouponDO> tWrapper = new QueryWrapper<>();
        tWrapper.eq("user_id", userId);
        CouponDO couponDO = couponMapper.selectOne(tWrapper);

        if (couponDO == null) {
            throw new MarketBizException(MarketErrorCodeEnum.USER_COUPON_IS_NULL);
        }

        if (Objects.equals(couponDO.getUsed(), CouponUsedStatusEnum.USED.getCode())) {
            throw new MarketBizException(MarketErrorCodeEnum.USER_COUPON_IS_USED);
        }

        couponDO.setUsed(CouponUsedStatusEnum.USED.getCode());
        couponDO.setUsedTime(new Date());
        couponMapper.updateById(couponDO);
        return true;
    }
}
