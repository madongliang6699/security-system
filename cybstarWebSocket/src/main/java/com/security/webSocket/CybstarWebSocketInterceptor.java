package com.security.webSocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * WebSocketInterceptor鉴权拦截器
 */
public class CybstarWebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 握手之前
     *
     * @param request    request
     * @param response   response
     * @param wsHandler  handler
     * @param attributes 属性
     * @return 是否握手成功：true-成功，false-失败
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //这里做客户端鉴权业务处理
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        String token = serverHttpRequest.getServletRequest().getHeader("Sec-WebSocket-Protocol");
        System.out.println("token="+token);

        //获取参数
        String userId = serverHttpRequest.getServletRequest().getParameter("userId");
        System.out.println("userId="+userId);
        String pwd = serverHttpRequest.getServletRequest().getParameter("pwd");
        System.out.println("pwd="+pwd);

        //todo 这里根据自己业务对userId和pwd进行校验，校验通过则返回true, 失败返回false
        //....
        //这里放入uid是个人需求，因为在MyWebSocketHandler我有对在线人数进行统计需要，还有对每个session存储对应关系。
        attributes.put("uid", userId);
        //这里对获取到的 token 授权码进行业务校验如 jwt 校验
        //...
        //在后端握手时设置一下请求头（Sec-WebSocket-Protocol），前端发来什么授权值，这里就设置什么值，不设置会报错导致建立连接成功后立即被关闭
        serverHttpResponse.getServletResponse().setHeader("Sec-WebSocket-Protocol", token);
        if("1".equals(userId)){
            return false;
        }else {
            return true;
        }
    }


    /**
     * 握手后
     *
     * @param request   request
     * @param response  response
     * @param wsHandler wsHandler
     * @param exception exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

        System.out.println("握手 成功!");
    }
}
