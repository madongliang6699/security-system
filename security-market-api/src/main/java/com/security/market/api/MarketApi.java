package com.security.market.api;

import com.security.common.core.JsonResult;
import com.security.market.domain.dto.CalculateOrderAmountDTO;
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
    JsonResult<CalculateOrderAmountDTO> calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest);

}