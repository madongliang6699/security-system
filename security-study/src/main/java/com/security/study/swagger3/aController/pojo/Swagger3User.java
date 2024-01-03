package com.security.study.swagger3.aController.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户信息")
public class Swagger3User implements Serializable {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("住址")
    private String addr;


}
