package com.security.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.security.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
@Data
@TableName("order_info")
@EqualsAndHashCode(callSuper = true)
public class OrderInfoEntity extends BaseEntity {

    //订单编号
    private String orderId;
    //订单类型 1:⼀般订单 255:其它
    private Integer orderType;
    //订单状态 10:已创建, 30:已履约,40:出库, 50:配送中, 60:已签收, 70:已取消, 100:已拒收, 255:⽆效订单
    private Integer orderStatus;
    //订单取消类型
    private Integer cancelType;
    //订单取消时间
    private Date cancelTime;
    //卖家编号
    private String sellerId;
    //买家编号
    private String userId;
    //交易总⾦额（以分为单位存储）
    private BigDecimal totalAmount;
    //交易⽀付⾦额
    private BigDecimal payAmount;
    //⽀付⽅式 10:微信⽀付 20:⽀付宝⽀付
    private Integer payType;
    //使⽤的优惠券编号
    private String couponId;
    //⽀付时间
    private Date payTime;
    //⽀付订单截⽌时间
    private Date expireTime;
    //⽤户备注
    private String userRemark;
    //订单删除状态 0:未删除 1:已删除
    private Integer deleteStatus;
    //订单评论状态 0:未发表评论 1:已发表评论
    private Integer commentStatus;
    //扩展信息
    private String extJson;


}
