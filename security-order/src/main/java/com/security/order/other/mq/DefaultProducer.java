package com.security.order.other.mq;

import com.alibaba.fastjson.JSONObject;
import com.security.common.constants.RocketMqConstant;
import com.security.order.other.exception.OrderBizException;
import com.security.order.other.exception.OrderErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * MQ 服务 【rocketMQ】
 */
@Slf4j
@Component
public class DefaultProducer {

    DefaultMQProducer defaultMQProducer;


    public DefaultProducer(RocketMQProperties rocketMQProperties) {
        this.defaultMQProducer = new DefaultMQProducer();
        this.defaultMQProducer.setProducerGroup(RocketMqConstant.ORDER_DEFAULT_PRODUCER_GROUP);
        this.defaultMQProducer.setNamesrvAddr(rocketMQProperties.getNameServer());
        System.out.println("==========="+ JSONObject.toJSONString(defaultMQProducer));
        //todo 这里先注释掉,因为还没有rocketMQ服务
        //start();
        System.out.println("==========="+ JSONObject.toJSONString(defaultMQProducer));

    }

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.defaultMQProducer.start();
        } catch (MQClientException e) {
            log.error("producer start error", e);
        }
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.defaultMQProducer.shutdown();
    }

    /**
     * 发送mq消息
     *
     * @param topic
     * @param msg
     * @param type
     */
    public void sendMessage(String topic, String msg, String type) {
        sendMessage(topic, msg, -1, type);
    }


    /**
     * 发送mq消息
     *
     * @param topic
     * @param msg
     * @param delayTimeLevel
     * @param type
     */
    public void sendMessage(String topic, String msg, Integer delayTimeLevel, String type) {
        Message message = new Message(topic, msg.getBytes(StandardCharsets.UTF_8));
        try {
            if (delayTimeLevel > 0) {
                message.setDelayTimeLevel(delayTimeLevel);
            }
            SendResult send = defaultMQProducer.send(message, 5000);
            if (SendStatus.SEND_OK == send.getSendStatus()) {
                log.info("发送MQ消息成功, type:{}, message:{}", type, message);
            } else {
                log.info("发送MQ消息失败, type:{}, message:{}", type, message);
                throw new OrderBizException(send.getSendStatus().toString());
            }
        } catch (Exception ex) {
            log.error("发送MQ消息失败：", ex);
            throw new OrderBizException(OrderErrorCodeEnum.SEND_MQ_FAILED);
        }
    }


}
