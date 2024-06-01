package com.security.market.api.impl;

import com.security.common.core.JsonResult;
import com.security.market.domain.request.CalculateOrderAmountRequest;
import com.security.market.other.exception.MarketBizException;
import com.security.market.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
// version = "2.0.0"
//@DubboService(version = "2.0.0", interfaceClass = MarketApi.class, retries = 0)
public class MarketApiImpl2 /*implements MarketApi*/ {


    @Autowired
    MarketService marketService;

//    @Override
    public JsonResult<Long> calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest) {
        try {
//            CalculateOrderAmountDTO calculateOrderAmountDTO = marketService.calculateOrderAmount(calculateOrderAmountRequest);

            return JsonResult.buildSuccess(124L);
        } catch (MarketBizException e) {
            log.error("biz error", e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error", e);
            return JsonResult.buildError(e.getMessage());
        }

    }

//    @Override
    public JsonResult<Boolean> lockUserCoupon(String userId) {
        return null;
    }
}