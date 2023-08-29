package com.security.market.api.impl;

import com.security.common.core.JsonResult;
import com.security.market.api.MarketApi;
import com.security.market.domain.dto.CalculateOrderAmountDTO;
import com.security.market.domain.request.CalculateOrderAmountRequest;
import com.security.market.exception.MarketBizException;
import com.security.market.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
// version = "2.0.0"
@DubboService(version = "2.0.0", interfaceClass = MarketApi.class, retries = 0)
public class MarketApiImpl2 implements MarketApi {


    @Autowired
    MarketService marketService;

    @Override
    public JsonResult<Long> calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest) {
        try {
//            CalculateOrderAmountDTO calculateOrderAmountDTO = marketService.calculateOrderAmount(calculateOrderAmountRequest);


            List<CalculateOrderAmountDTO.OrderAmountDetailDTO> orderAmountDetailDTOS = new ArrayList<>();
            orderAmountDetailDTOS.add(new CalculateOrderAmountDTO.OrderAmountDetailDTO(){{
                setAmount(12);
                setOrderId(calculateOrderAmountRequest.getOrderId());
                setAmountType(1);
                setSalePrice(1234);
                setSkuCode("234");
                setProductType(12);
                setSaleQuantity(1234);
            }});

            List<CalculateOrderAmountDTO.OrderAmountDTO> orderAmountDTOS = new ArrayList<>();
            orderAmountDTOS.add(new CalculateOrderAmountDTO.OrderAmountDTO(){{
                setAmount(123);
                setOrderId(calculateOrderAmountRequest.getOrderId());
                setAmountType(2);
            }});

            CalculateOrderAmountDTO calculateOrderAmountDTO = new CalculateOrderAmountDTO();
            calculateOrderAmountDTO.setOrderAmountDetail(orderAmountDetailDTOS);
            calculateOrderAmountDTO.setOrderAmountList(orderAmountDTOS);

            return JsonResult.buildSuccess(124L);
        } catch (MarketBizException e) {
            log.error("biz error", e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error", e);
            return JsonResult.buildError(e.getMessage());
        }

    }

    @Override
    public JsonResult<Boolean> lockUserCoupon(String userId) {
        return null;
    }
}