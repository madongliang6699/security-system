package com.security.order.service.impl;

import com.security.common.utils.LoggerFormat;
import com.security.common.utils.ParamCheckUtil;
import com.security.common.utils.RandomUtil;
import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.GenOrderIdRequest;
import com.security.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    /**
     * 生成订单号
     *
     * @param genOrderIdRequest 生成订单号入参
     *
     * @return 订单号
     */
    public GenOrderIdDTO genOrderId(GenOrderIdRequest genOrderIdRequest) {
        logger.info(LoggerFormat.build().remark("genOrderId->request").data("request", genOrderIdRequest).finish());
        
        // 参数检查
        String userId = genOrderIdRequest.getUserId();
        ParamCheckUtil.checkStringNonEmpty(userId);
        Integer businessIdentifier = genOrderIdRequest.getBusinessIdentifier();
        ParamCheckUtil.checkObjectNonNull(businessIdentifier);
        
        //todo 这里单号的生成逻辑先不研究，里面挺复杂的。先临时造一个随机的。
        // String orderId = orderNoManager.genOrderId(OrderNoTypeEnum.SALE_ORDER.getCode(), userId);
        String orderId = RandomUtil.genRandomNumberStr(12);
        GenOrderIdDTO genOrderIdDTO = new GenOrderIdDTO();
        genOrderIdDTO.setOrderId(orderId);
        
        return genOrderIdDTO;
    }
    
    
}
