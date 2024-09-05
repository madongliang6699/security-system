package com.security.controller;


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
public class TestController {


    /**
     *
     */
    @GetMapping("/test1")
    public JsonResult<String> test1(@Param("name") String name) {
        return JsonResult.buildSuccess("okkk");
    }


}
