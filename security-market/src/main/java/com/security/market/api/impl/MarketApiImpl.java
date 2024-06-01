package com.security.market.api.impl;

import com.security.common.core.JsonResult;
import com.security.market.api.MarketApi;
import com.security.market.other.exception.MarketBizException;
import com.security.market.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Slf4j
// version = "1.0.0"是指定MarketApi这个接口服务的一个版本号，就是：我们把MarketApi接口看作一个对外提供业务的服务，
// 但是MarketApi接口可能有多个实现类，那就是有多个服务版本号，dubbo支持使用版本号来区分同一个接口的不同实现类提供的服务，
// 调用方可以指定版本号来选择使用哪个服务。
@DubboService(version = "1.0.0", interfaceClass = MarketApi.class, retries = 0)
public class MarketApiImpl implements MarketApi {


    @Autowired
    CouponService couponService;


    @Override
    public JsonResult<Boolean> lockUserCoupon(String userId) {
        try {
            Boolean b = couponService.lockUserCoupon(userId);
            return JsonResult.buildSuccess(b);
        } catch (MarketBizException e) {
            log.error("biz error", e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error", e);
            return JsonResult.buildError(e.getMessage());
        }
    }

    @Override
    public JsonResult<String> ceshi1(String userId) {
        try {
            return JsonResult.buildSuccess("nihao啊");
        } catch (MarketBizException e) {
            log.error("biz error", e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error", e);
            return JsonResult.buildError(e.getMessage());
        }
    }


}