package com.security.order.api.Impl;

import com.security.common.core.JsonResult;
import com.security.order.api.OrderApi;
import com.security.order.domain.dto.CreateOrderDTO;
import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;
import com.security.order.exception.OrderBizException;
import com.security.order.exception.OrderErrorCodeEnum;
import com.security.order.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0.0", interfaceClass = OrderApi.class, retries = 0)
public class OrderApiImpl implements OrderApi {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderApiImpl.class);
    
    @Autowired
    OrderService orderService;
    
    
    @Override
    public JsonResult<GenOrderIdDTO> genOrderNo(GenOrderIdRequest genOrderIdRequest) {
        try {
            String userId = genOrderIdRequest.getUserId();
            if (userId == null || "".equals(userId)) {
                return JsonResult.buildError(OrderErrorCodeEnum.USER_ID_IS_NULL);
            }
            GenOrderIdDTO genOrderIdDTO = orderService.genOrderId(genOrderIdRequest);
            return JsonResult.buildSuccess(genOrderIdDTO);
        }catch (OrderBizException exception){
            logger.error("biz error", exception);
            return JsonResult.buildError(exception.getErrorCode(), exception.getErrorMsg());
        }catch (Exception exception){
            logger.error("system error", exception);
            return JsonResult.buildError(exception.getMessage());
        }
    
        /**
         * 上面这么麻烦的构建返回异常的代码，应该是这个类是是Dubbo的类，spring的全局异常处理管不到这里的异常。
         * 但是后来发现Dubbo的类也是注册到IOC容器中的啊，
         */
    
    }
    
    @Override
    public JsonResult<CreateOrderDTO> createOrder(CreateOrderRequest createOrderRequest) {
        try {
            CreateOrderDTO createOrderDTO = orderService.createOrder(createOrderRequest);
            return JsonResult.buildSuccess(createOrderDTO);
        }catch (OrderBizException exception){
            logger.error("biz error", exception);
            return JsonResult.buildError(exception.getErrorCode(), exception.getErrorMsg());
        }catch (Exception exception){
            logger.error("system error", exception);
            return JsonResult.buildError(exception.getMessage());
        }
    }
}
