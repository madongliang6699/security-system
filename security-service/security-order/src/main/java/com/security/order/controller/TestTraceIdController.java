package com.security.order.controller;


import cn.hutool.system.UserInfo;
import com.security.common.core.JsonResult;
import com.security.order.service.TestTraceIdService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 测试日志追踪功能 traceId
 */
@RestController
@RequestMapping("/testTraceId")
@Log4j2
public class TestTraceIdController {

    @Resource
    TestTraceIdService testTraceIdService;

    /**
     * 测试接口1
     */
    @GetMapping("/test1")
    public JsonResult<String> test1(@Param("name") String name) {
        log.info("--traceId是：");

        String s = testTraceIdService.test1(name);
        return JsonResult.buildSuccess(s);
    }


}
