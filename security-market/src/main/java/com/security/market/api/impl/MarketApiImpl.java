package com.security.market.api.impl;

import com.security.common.core.JsonResult;
import com.security.market.api.MarketApi;
import com.security.market.domain.dto.CalculateOrderAmountDTO;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@DubboService(version = "1.0.0", interfaceClass = MarketApi.class, retries = 0)
public class MarketApiImpl implements MarketApi {

    @Override
    public JsonResult<CalculateOrderAmountDTO> calculateOrderAmount(com.security.market.domain.request.CalculateOrderAmountRequest calculateOrderAmountRequest) {
        return null;
    }
}