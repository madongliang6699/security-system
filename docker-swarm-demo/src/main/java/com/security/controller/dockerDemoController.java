package com.security.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 测试日志追踪功能 traceId
 */
@RestController
@Log4j2
public class dockerDemoController {


    /**
     * dockerfile 内测试健康检查
     */
    @GetMapping("/healthcheck")
    public String test1() {

        Map<String, String> map = new HashMap<>();
        map.put("myKey", "按到");

        try {
            //容器的名称
            String containerHostname = System.getenv("HOSTNAME");
            //宿主机的ip
            String HostIP = System.getenv("HOST_IP");

            map.put("容器名称", containerHostname);
            map.put("宿主机IP地址", HostIP);
        } catch (Exception e) {
            log.error("健康检查异常: {}", e.getMessage());
        }


        return JSONUtil.toJsonStr(map);
    }


}
