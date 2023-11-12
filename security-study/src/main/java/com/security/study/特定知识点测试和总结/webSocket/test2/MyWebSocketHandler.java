package com.security.study.特定知识点测试和总结.webSocket.test2;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MyWebSocketHandler处理器
 */
public class MyWebSocketHandler extends TextWebSocketHandler {



    /**
     * 静态变量，用来记录当前在线连接数
     */
    private static AtomicInteger onlineNum = new AtomicInteger();

    /**
     * 存放每个客户端连接对象
     */
    private static ConcurrentHashMap<Integer, WebSocketSession> sessionPools = new ConcurrentHashMap<>();

    /**
     * 在线人数加一
     */
    public static void addOnlineCount() { onlineNum.incrementAndGet(); }

    /**
     * 在线人数减一
     */
    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }


    /**
     * 接受客户端消息
     *
     * @param session session
     * @param message message
     * @throws IOException e
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(new TextMessage(String.format("收到用户：【%s】发来的【%s】",
                session.getAttributes().get("uid"),
                message.getPayload())));
    }

    /**
     * 建立连接后发送消息给客户端
     *
     * @param session session
     * @throws Exception e
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uid = session.getAttributes().get("uid").toString();
        WebSocketSession put = sessionPools.put(Integer.parseInt(uid), session);
        if (put == null) {
            addOnlineCount();
        }
        session.sendMessage(new TextMessage("连接成功! 在线数量：" + onlineNum));
    }

    /**
     * 连接关闭后
     *
     * @param session session
     * @param status  status
     * @throws Exception e
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String uid = session.getAttributes().get("uid").toString();
        sessionPools.remove(Integer.parseInt(uid));
        subOnlineCount();
        System.out.println("关闭连接");
    }

    /**
     * 发送广播消息
     *
     * @param message 消息内容
     */
    public static void sendTopic(String message) {
        if (sessionPools.isEmpty()) {
            return;
        }
        for (Map.Entry<Integer, WebSocketSession> entry : sessionPools.entrySet()) {
            try {
                entry.getValue().sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 点对点发送消息
     *
     * @param uid     用户
     * @param message 消息
     */
    public static void sendToUser(String uid, String message) {
        WebSocketSession socketSession = sessionPools.get(Integer.parseInt(uid));
        if (socketSession == null) {
            return;
        }
        try {
            socketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            System.out.println("send to user:{"+uid+"}, error! data:{"+message+"}");
        }
    }


}
