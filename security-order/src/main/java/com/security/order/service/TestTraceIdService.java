package com.security.order.service;

import org.springframework.web.bind.annotation.PostMapping;

public interface TestTraceIdService {

    /**
     * 测试接口1
     */
    @PostMapping("/test1")
    String test1(String name);

}
