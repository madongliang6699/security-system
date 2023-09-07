package com.security.order.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.security.common.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("order_info")
public class OrderInfoDO extends BaseEntity {

    private String orderId;
    private Integer orderType;
    private Integer orderStatus;
    private Integer cancelType;
    private Date cancelTime;


}
