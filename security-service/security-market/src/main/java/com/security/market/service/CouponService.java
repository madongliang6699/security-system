package com.security.market.service;


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
