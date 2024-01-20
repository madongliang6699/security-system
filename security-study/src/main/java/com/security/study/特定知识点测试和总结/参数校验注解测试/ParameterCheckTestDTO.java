package com.security.study.特定知识点测试和总结.参数校验注解测试;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class ParameterCheckTestDTO implements Serializable {

    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 1, max = 2, message = "name长度必须在1-2之间")
    private String name;


    @Min(value = 50, message = "年龄不能小于50岁")
    @Max(value = 55, message = "年龄不应超过55岁")
    private Integer age;


    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String phone;


}
