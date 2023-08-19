package com.security.order.controller;


import com.security.common.core.JsonResult;
import com.security.order.api.OrderApi;
import com.security.order.domain.dto.GenOrderIdDTO;
import com.security.order.domain.request.GenOrderIdRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/test")
public class OrderTestController {
    
    
    //todo 奇怪，这里竟然也可以使用@Autowired注入OrderApi的实现类OrderApiImpl对象，但是OrderApiImpl是使用@DubboService标注的，难道@DubboService的类也是会注册到spring容器中。
    // 百度了一下，好像确实会注册到容器中。但是这里的情况比较特殊，这里的OrderApiImpl对象正好也是本模块项目中的类，也就是说，注册到了本项目的spring容器中。
    // 如果OrderApiImpl类是其他模块项目的@DubboService的类，注册到了其他项目的容器中，那这里使用@Autowired还能注入吗？ 待测试
    //@Autowired
    
    @DubboReference(version = "1.0.0", retries = 0) //使用dubbo注入，拿到的对象不是OrderApiImpl对象，是一个Proxy0的代理对象
    OrderApi orderApi;
    
    /**
     * 生成订单id
     */
    @RequestMapping("/genOrderId")
    public JsonResult<GenOrderIdDTO> genOrderId(@RequestBody GenOrderIdRequest genOrderIdRequest){
        return orderApi.genOrderNo(genOrderIdRequest);
    }


}
