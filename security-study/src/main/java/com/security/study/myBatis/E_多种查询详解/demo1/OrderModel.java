package com.security.study.myBatis.E_多种查询详解.demo1;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    
    private Integer id;
    private Integer userId;
    private Long createTime;
    private Long upTime;
    //下单用户信息
    private UserModel userModel;
    
    
}
