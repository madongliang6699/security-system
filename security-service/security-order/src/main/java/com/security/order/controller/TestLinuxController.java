package com.security.order.controller;


import com.security.common.core.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试线上问题(例如: CPU飙升,JVM问题 等)
 */
@RestController
@RequestMapping("/linux")
@Log4j2
public class TestLinuxController {

    //这里比较奇怪的是:不加volatile的话, 在linux系统上,修改cupTag的值之后,其他线程看不见修改后的值, 但是在Windows上就可以立马看到. 不同的操作系统或硬件,可能底层刷新数据或jvm的版本不一致,jvm的内存模型优化不一致.
    private volatile String cupTag = "on";

    /**
     * 测试cpu飙升
     */
    @GetMapping("/cpuUp")
    public JsonResult<String> test1(@Param("cupTag") String cupTag) throws InterruptedException {
        this.cupTag = cupTag;
        while ("on".equals(this.cupTag)){

        }
        System.out.println("退出方法:" + this.cupTag);
        return JsonResult.buildSuccess(cupTag);
    }


}
