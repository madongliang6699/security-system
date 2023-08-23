package com.security.market.service;


import com.security.market.domain.dto.CalculateOrderAmountDTO;
import com.security.market.domain.request.CalculateOrderAmountRequest;

/**
 * 营销管理service接口
 * @author zhonghuashishan
 *
 */
public interface MarketService {

	/**
	 * 计算订单费用
	 *
	 * @param calculateOrderAmountRequest
	 * @return
	 */
	CalculateOrderAmountDTO calculateOrderAmount(CalculateOrderAmountRequest calculateOrderAmountRequest);
	
}
