package com.security.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.StopWatch;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.common.constants.RocketDelayedLevel;
import com.security.common.constants.RocketMqConstant;
import com.security.common.core.JsonResult;
import com.security.common.enums.AmountTypeEnum;
import com.security.common.enums.OrderStatusEnum;
import com.security.common.enums.PayTypeEnum;
import com.security.common.message.PayOrderTimeoutDelayMessage;
import com.security.common.utils.JsonUtil;
import com.security.common.utils.LoggerFormat;
import com.security.common.utils.ParamCheckUtil;
import com.security.common.utils.RandomUtil;
import com.security.inventory.api.InventoryApi;
import com.security.inventory.domain.request.LockProductStockRequest;
import com.security.market.api.MarketApi;
import com.security.order.domain.request.CreateOrderRequest;
import com.security.order.domain.request.GenOrderIdRequest;
import com.security.order.domain.response.CreateOrderResponse;
import com.security.order.domain.response.GenOrderIdResponse;
import com.security.order.mapper.OrderInfoMapper;
import com.security.order.model.dto.OrderInfoDTO;
import com.security.order.model.dto.OrderItemDTO;
import com.security.order.model.entity.OrderInfoEntity;
import com.security.order.other.enums.*;
import com.security.order.other.exception.OrderBizException;
import com.security.order.other.exception.OrderErrorCodeEnum;
import com.security.order.other.mq.DefaultProducer;
import com.security.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfoEntity> implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @DubboReference(version = "1.0.0", retries = 0, timeout = 30 * 1000)
    MarketApi marketApi;

    @DubboReference(version = "1.0.0", retries = 0, timeout = 30 * 1000)
    InventoryApi inventoryApi;

    @Autowired
    DefaultProducer defaultProducer;

    @Autowired
    OrderInfoMapper orderInfoMapper;


    /**
     * 下订单
     */
    public String placeOrder(OrderInfoDTO orderInfoDTO) {
        OrderInfoEntity orderInfoEntity = orderInfoDTO.clone(OrderInfoEntity.class);
        orderInfoEntity.setOrderId(UUID.randomUUID().toString());
        StopWatch stopWatch = new StopWatch("测试时间2");
        stopWatch.start("1");
        JsonResult<Boolean> jsonResult = marketApi.lockUserCoupon(orderInfoDTO.getUserId());
        if (!jsonResult.getSuccess()) {
            logger.error("调用营销服务 锁定优惠券失败，错误码：{}，错误信息：{}", jsonResult.getErrorCode(), jsonResult.getErrorMessage());
            throw new OrderBizException(jsonResult.getErrorCode(), jsonResult.getErrorMessage());
        }
        stopWatch.stop();

        stopWatch.start("2");
        List<OrderItemDTO> itemDTOList = orderInfoDTO.getItemDTOList();
        for (OrderItemDTO orderItemDTO : itemDTOList) {
            LockProductStockRequest request = new LockProductStockRequest();
            request.setProductId(orderItemDTO.getProductId());
            request.setQuantity(orderItemDTO.getSaleQuantity());
            System.out.println("lockProductStock未进："+System.currentTimeMillis());
            JsonResult<Boolean> lockProductStockJsonRes = inventoryApi.lockProductStock(request);
            if (!lockProductStockJsonRes.getSuccess()) {
                logger.error("调用库存服务 锁定库存失败，错误码：{}，错误信息：{}", lockProductStockJsonRes.getErrorCode(), lockProductStockJsonRes.getErrorMessage());
                throw new OrderBizException(lockProductStockJsonRes.getErrorCode(), lockProductStockJsonRes.getErrorMessage());
            }
        }
        stopWatch.stop();

        stopWatch.start("3");
        orderInfoMapper.insert(orderInfoEntity);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
        return orderInfoEntity.getOrderId();
    }

    @Override
    public List<OrderInfoDTO> selectAllOrder() {
        QueryWrapper<OrderInfoEntity> tWrapper = new QueryWrapper<>();
        List<OrderInfoEntity> orderInfoEntities = orderInfoMapper.selectList(tWrapper);
        List<OrderInfoDTO> orderInfoDTOS = BeanUtil.copyToList(orderInfoEntities, OrderInfoDTO.class);
        return orderInfoDTOS;
    }


    /**
     * 生成订单号
     *
     * @param genOrderIdRequest 生成订单号入参
     * @return 订单号
     */
    public GenOrderIdResponse genOrderId(GenOrderIdRequest genOrderIdRequest) {
        logger.info(LoggerFormat.build().remark("genOrderId->request").data("request", genOrderIdRequest).finish());

        // 参数检查
        String userId = genOrderIdRequest.getUserId();
        ParamCheckUtil.checkStringNonEmpty(userId);
        Integer businessIdentifier = genOrderIdRequest.getBusinessIdentifier();
        ParamCheckUtil.checkObjectNonNull(businessIdentifier);

        //todo 这里单号的生成逻辑先不研究，里面挺复杂的。先临时造一个随机的。
        // String orderId = orderNoManager.genOrderId(OrderNoTypeEnum.SALE_ORDER.getCode(), userId);
        String orderId = RandomUtil.genRandomNumberStr(12);
        GenOrderIdResponse genOrderIdResponse = new GenOrderIdResponse();
        genOrderIdResponse.setOrderId(orderId);

        return genOrderIdResponse;
    }


    /**
     * 提交订单/生成订单接口
     *
     * @param createOrderRequest 提交订单请求入参
     * @return 订单号
     */
    @GlobalTransactional
    @Transactional
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        // 1、入参检查
        checkCreateOrderRequestParam(createOrderRequest);
        // 2、风控检查。这里省略
        // 3、获取商品信息。这里省略

//        //region 4、计算订单价格。
//        // 调用营销服务计算订单价格
//        JsonResult<Long> jsonResult = marketApi.calculateOrderAmount(null);
//        if (!jsonResult.getSuccess()) {
//            throw new OrderBizException(jsonResult.getErrorCode(), jsonResult.getErrorMessage());
//        }
//        Long data = jsonResult.getData();
//        if (data == null) {
//            throw new OrderBizException(OrderErrorCodeEnum.CALCULATE_ORDER_AMOUNT_ERROR);
//        }
//        //endregion


        //region 5、锁定优惠券。
        JsonResult<Boolean> booleanJsonResult = marketApi.lockUserCoupon("小明");
        if (!booleanJsonResult.getSuccess()) {
            logger.info("异常：{}，{}", booleanJsonResult.getErrorCode(), booleanJsonResult.getErrorMessage());
            throw new OrderBizException(booleanJsonResult.getErrorCode(), booleanJsonResult.getErrorMessage());
        }
        //endregion


        //region 5、锁定商品库存。
        JsonResult<Boolean> booleanJsonResult1 = inventoryApi.lockProductStock(new LockProductStockRequest());
        if (!booleanJsonResult1.getSuccess()) {
            logger.info("异常：{}，{}", booleanJsonResult1.getErrorCode(), booleanJsonResult1.getErrorMessage());
            throw new OrderBizException(booleanJsonResult1.getErrorCode(), booleanJsonResult1.getErrorMessage());
        }
        //endregion


        //region 6、生成订单入库。
        save(new OrderInfoEntity() {{
            setOrderId(createOrderRequest.getOrderId());
            setOrderStatus(OrderStatusEnum.CREATED.getCode());
            setOrderType(OrderTypeEnum.NORMAL.getCode());
            setCancelTime(new Date());
            setCancelType(OrderCancelTypeEnum.TIMEOUT_CANCELED.getCode());
            setGmtModified(new Date());
            setGmtCreate(new Date());
        }});
        //endregion


        //region 7、发送订单延迟消息用于支付超时自动关单。
        PayOrderTimeoutDelayMessage message = new PayOrderTimeoutDelayMessage();

        message.setOrderId(createOrderRequest.getOrderId());
        message.setBusinessIdentifier(createOrderRequest.getBusinessIdentifier());
        message.setCancelType(OrderCancelTypeEnum.TIMEOUT_CANCELED.getCode());
        message.setUserId(createOrderRequest.getUserId());
        message.setOrderType(createOrderRequest.getOrderType());
        message.setOrderStatus(OrderStatusEnum.CREATED.getCode());

        String msgJson = JsonUtil.object2Json(message);
        defaultProducer.sendMessage(RocketMqConstant.PAY_ORDER_TIMEOUT_DELAY_TOPIC, msgJson,
                RocketDelayedLevel.DELAYED_30s, "支付订单超时延迟消息");
        //endregion

        //返回订单创建成功
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setOrderId(createOrderRequest.getOrderId());
        return createOrderResponse;
    }


    /**
     * 检查创建订单请求参数
     */
    private void checkCreateOrderRequestParam(CreateOrderRequest createOrderRequest) {
        ParamCheckUtil.checkObjectNonNull(createOrderRequest);

        // 订单ID
        String orderId = createOrderRequest.getOrderId();
        ParamCheckUtil.checkStringNonEmpty(orderId, OrderErrorCodeEnum.ORDER_ID_IS_NULL);

        // 业务线标识
        Integer businessIdentifier = createOrderRequest.getBusinessIdentifier();
        ParamCheckUtil.checkObjectNonNull(businessIdentifier, OrderErrorCodeEnum.BUSINESS_IDENTIFIER_IS_NULL);
        if (BusinessIdentifierEnum.getByCode(businessIdentifier) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.BUSINESS_IDENTIFIER_ERROR);
        }

        // 用户ID
        String userId = createOrderRequest.getUserId();
        ParamCheckUtil.checkStringNonEmpty(userId, OrderErrorCodeEnum.USER_ID_IS_NULL);

        // 订单类型
        Integer orderType = createOrderRequest.getOrderType();
        ParamCheckUtil.checkObjectNonNull(businessIdentifier, OrderErrorCodeEnum.ORDER_TYPE_IS_NULL);
        if (OrderTypeEnum.getByCode(orderType) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.ORDER_TYPE_ERROR);
        }

        // 卖家ID
        String sellerId = createOrderRequest.getSellerId();
        ParamCheckUtil.checkStringNonEmpty(sellerId, OrderErrorCodeEnum.SELLER_ID_IS_NULL);

        // 配送类型
        Integer deliveryType = createOrderRequest.getDeliveryType();
        ParamCheckUtil.checkObjectNonNull(deliveryType, OrderErrorCodeEnum.USER_ADDRESS_ERROR);
        if (DeliveryTypeEnum.getByCode(deliveryType) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.DELIVERY_TYPE_ERROR);
        }

        // 地址信息
        String province = createOrderRequest.getProvince();
        String city = createOrderRequest.getCity();
        String area = createOrderRequest.getArea();
        String streetAddress = createOrderRequest.getStreet();
        ParamCheckUtil.checkStringNonEmpty(province, OrderErrorCodeEnum.USER_ADDRESS_ERROR);
        ParamCheckUtil.checkStringNonEmpty(city, OrderErrorCodeEnum.USER_ADDRESS_ERROR);
        ParamCheckUtil.checkStringNonEmpty(area, OrderErrorCodeEnum.USER_ADDRESS_ERROR);
        ParamCheckUtil.checkStringNonEmpty(streetAddress, OrderErrorCodeEnum.USER_ADDRESS_ERROR);

        // 区域ID
        String regionId = createOrderRequest.getRegionId();
        ParamCheckUtil.checkStringNonEmpty(regionId, OrderErrorCodeEnum.REGION_ID_IS_NULL);

        // 经纬度
        BigDecimal lon = createOrderRequest.getLon();
        BigDecimal lat = createOrderRequest.getLat();
        ParamCheckUtil.checkObjectNonNull(lon, OrderErrorCodeEnum.USER_LOCATION_IS_NULL);
        ParamCheckUtil.checkObjectNonNull(lat, OrderErrorCodeEnum.USER_LOCATION_IS_NULL);

        // 收货人信息
        String receiverName = createOrderRequest.getReceiverName();
        String receiverPhone = createOrderRequest.getReceiverPhone();
        ParamCheckUtil.checkStringNonEmpty(receiverName, OrderErrorCodeEnum.ORDER_RECEIVER_IS_NULL);
        ParamCheckUtil.checkStringNonEmpty(receiverPhone, OrderErrorCodeEnum.ORDER_RECEIVER_IS_NULL);

        // 客户端设备信息
        String clientIp = createOrderRequest.getClientIp();
        ParamCheckUtil.checkStringNonEmpty(clientIp, OrderErrorCodeEnum.CLIENT_IP_IS_NULL);

        // 商品条目信息
        List<CreateOrderRequest.OrderItemRequest> orderItemRequestList = createOrderRequest.getOrderItemRequestList();
        ParamCheckUtil.checkCollectionNonEmpty(orderItemRequestList, OrderErrorCodeEnum.ORDER_ITEM_IS_NULL);

        for (CreateOrderRequest.OrderItemRequest orderItemRequest : orderItemRequestList) {
            Integer productType = orderItemRequest.getProductType();
            Integer saleQuantity = orderItemRequest.getSaleQuantity();
            String skuCode = orderItemRequest.getSkuCode();
            ParamCheckUtil.checkObjectNonNull(productType, OrderErrorCodeEnum.ORDER_ITEM_PARAM_ERROR);
            ParamCheckUtil.checkObjectNonNull(saleQuantity, OrderErrorCodeEnum.ORDER_ITEM_PARAM_ERROR);
            ParamCheckUtil.checkStringNonEmpty(skuCode, OrderErrorCodeEnum.ORDER_ITEM_PARAM_ERROR);
        }

        // 订单费用信息
        List<CreateOrderRequest.OrderAmountRequest> orderAmountRequestList = createOrderRequest.getOrderAmountRequestList();
        ParamCheckUtil.checkCollectionNonEmpty(orderAmountRequestList, OrderErrorCodeEnum.ORDER_AMOUNT_IS_NULL);

        for (CreateOrderRequest.OrderAmountRequest orderAmountRequest : orderAmountRequestList) {
            Integer amountType = orderAmountRequest.getAmountType();
            ParamCheckUtil.checkObjectNonNull(amountType, OrderErrorCodeEnum.ORDER_AMOUNT_TYPE_IS_NULL);

            if (AmountTypeEnum.getByCode(amountType) == null) {
                throw new OrderBizException(OrderErrorCodeEnum.ORDER_AMOUNT_TYPE_PARAM_ERROR);
            }
        }
        Map<Integer, Integer> orderAmountMap = orderAmountRequestList.stream()
                .collect(Collectors.toMap(CreateOrderRequest.OrderAmountRequest::getAmountType,
                        CreateOrderRequest.OrderAmountRequest::getAmount));

        // 订单支付原价不能为空
        if (orderAmountMap.get(AmountTypeEnum.ORIGIN_PAY_AMOUNT.getCode()) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.ORDER_ORIGIN_PAY_AMOUNT_IS_NULL);
        }
        // 订单运费不能为空
        if (orderAmountMap.get(AmountTypeEnum.SHIPPING_AMOUNT.getCode()) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.ORDER_SHIPPING_AMOUNT_IS_NULL);
        }
        // 订单实付金额不能为空
        if (orderAmountMap.get(AmountTypeEnum.REAL_PAY_AMOUNT.getCode()) == null) {
            throw new OrderBizException(OrderErrorCodeEnum.ORDER_REAL_PAY_AMOUNT_IS_NULL);
        }
        if (StringUtils.isNotEmpty(createOrderRequest.getCouponId())) {
            // 订单优惠券抵扣金额不能为空
            if (orderAmountMap.get(AmountTypeEnum.COUPON_DISCOUNT_AMOUNT.getCode()) == null) {
                throw new OrderBizException(OrderErrorCodeEnum.ORDER_DISCOUNT_AMOUNT_IS_NULL);
            }
        }

        // 订单支付信息
        List<CreateOrderRequest.PaymentRequest> paymentRequestList = createOrderRequest.getPaymentRequestList();
        ParamCheckUtil.checkCollectionNonEmpty(paymentRequestList, OrderErrorCodeEnum.ORDER_PAYMENT_IS_NULL);

        for (CreateOrderRequest.PaymentRequest paymentRequest : paymentRequestList) {
            Integer payType = paymentRequest.getPayType();
            Integer accountType = paymentRequest.getAccountType();
            if (payType == null || PayTypeEnum.getByCode(payType) == null) {
                throw new OrderBizException(OrderErrorCodeEnum.PAY_TYPE_PARAM_ERROR);
            }
            if (accountType == null || AccountTypeEnum.getByCode(accountType) == null) {
                throw new OrderBizException(OrderErrorCodeEnum.ACCOUNT_TYPE_PARAM_ERROR);
            }
        }

    }

}
