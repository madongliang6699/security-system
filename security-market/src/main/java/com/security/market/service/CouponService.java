package com.security.market.service;


import com.security.market.domain.request.CalculateOrderAmountRequest;

/**
 * 营销管理service接口
 *
 * @author zhonghuashishan
 */
public interface CouponService {

    /**
     * 锁定优惠券
     *
     * @param userId
     * @return
     */
    Boolean lockUserCoupon(String userId);

}
