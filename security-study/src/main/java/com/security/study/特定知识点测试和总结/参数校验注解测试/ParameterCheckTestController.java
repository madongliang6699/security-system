package com.security.study.特定知识点测试和总结.参数校验注解测试;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ceshi")
public class ParameterCheckTestController {

    @RequestMapping("/aa")
    public String aa(@Valid @RequestBody ParameterCheckTestDTO parameterCheckTestDTO) {
        System.out.println(parameterCheckTestDTO);
        return "xxx";
    }

}
