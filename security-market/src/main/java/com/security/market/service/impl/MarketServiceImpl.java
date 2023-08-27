package com.security.market.service.impl;


import com.security.market.domain.request.CalculateOrderAmountRequest;
import com.security.market.service.MarketService;
import org.springframework.stereotype.Service;

/**
 * 营销管理service组件
 *
 * @author zhonghuashishan
 */
@Service
public class MarketServiceImpl implements MarketService {


    @Override
    public Long calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest) {
        return 1212L;
    }
}
