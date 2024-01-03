package com.security.study.swagger3.bController.aController;

import com.security.study.swagger3.aController.pojo.Swagger3User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Swagger3_B_Controller业务模块")
@RestController
@RequestMapping("/swagger3b")
public class Swagger3_B_Controller {



    @ApiOperation("根据id查询")
    @GetMapping("/selectById")
    public void aa(String aa, Integer bb, Long cc, Float dd, Swagger3User swagger3User){
        System.out.println("sssssssrgfs");
    }

    @ApiOperation(value = "分页查询", notes = "支持keyword参数模糊查询（作用于name和addr字段）",
            tags = "tags测试", nickname = "nickname测试")
    @PostMapping("/selectByPage")
    public String selectTwo(String aa, Integer bb, Long cc, Float dd, Swagger3User swagger3User){
        System.out.println("sssssssrgfs");
        return "haha";
    }

}
