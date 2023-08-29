package com.security.market.service.impl;


import com.security.market.domain.request.CalculateOrderAmountRequest;
import com.security.market.entity.FreightTemplateDO;
import com.security.market.mapper.FreightTemplateMapper;
import com.security.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 营销管理service组件
 *
 * @author zhonghuashishan
 */
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    FreightTemplateMapper freightTemplateMapper;


    @Override
    public Long calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest) {
        FreightTemplateDO freightTemplateDO = freightTemplateMapper.selectById(1);

        return freightTemplateDO.getShippingAmount().longValue();
    }


}
