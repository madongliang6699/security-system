package com.security.market.api;

import com.security.common.core.JsonResult;
import com.security.market.domain.request.CalculateOrderAmountRequest;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
public interface MarketApi {

    /**
     * 计算订单费用
     *
     * @param calculateOrderAmountRequest
     * @return
     */
    JsonResult<Long> calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest);


    /**
     * 计算订单费用
     *
     * @param userId
     * @return
     */
    JsonResult<Boolean> lockUserCoupon(String userId);


    /**
     * 测试zk挂掉后能不能使用
     *
     * @param userId
     * @return
     */
    JsonResult<String> ceshi1(String userId);

}