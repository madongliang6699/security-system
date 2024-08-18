package com.security.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.security.common.core.JsonResult;
import com.security.inventory.api.InventoryTestTraceIdApi;
import com.security.order.other.exception.OrderBizException;
import com.security.order.service.TestTraceIdService;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TestTraceIdServiceImpl implements TestTraceIdService {


    @DubboReference(version = "1.0.0")
    InventoryTestTraceIdApi inventoryTestTraceIdApi;

    @Override
    public String test1(String name) {
        JsonResult<String> stringJsonResult = inventoryTestTraceIdApi.test1(name);
        log.info("-------库存服务返回数据：{}", JSONObject.toJSONString(stringJsonResult));
        if (stringJsonResult.getSuccess()){
            return stringJsonResult.getData();
        } else {
            throw new OrderBizException(StrUtil.format("调用库存服务异常:{}", stringJsonResult.getErrorMessage()));
        }
    }
}
