package com.security.order.api;

import com.security.common.core.JsonResult;
import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.GenOrderIdRequest;

public interface OrderApi {
    
    /**
     * 生成订单号
     */
    JsonResult<GenOrderIdDTO> genOrderNo(GenOrderIdRequest genOrderIdRequest);


}
