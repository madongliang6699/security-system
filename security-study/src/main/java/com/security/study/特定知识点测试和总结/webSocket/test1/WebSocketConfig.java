package com.security.study.特定知识点测试和总结.webSocket.test1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

/**
 * WebSocket配置类。开启WebSocket的支持
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator{

    /**
     * bean注册：会自动扫描带有@ServerEndpoint注解声明的Websocket Endpoint(端点)，注册成为Websocket bean。
     * 要注意，如果项目使用外置的servlet容器，而不是直接使用springboot内置容器的话，就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }



    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        // 这个userProperties 可以通过 session.getUserProperties()获取
        final Map<String, Object> userProperties = sec.getUserProperties();

        // 获取token
        //Map<String, List<String>> headers = request.getHeaders();
        //List<String> cookie = headers.get("cookie");
        //String token = "";
        //if (cookie != null) {
        //    token = CookieUtil.getCookie("token", cookie.get(0));
        //}
        //
        //// 判断用户token是否合法，并获取用户id，入果非法，生成一个临时用户用于识别
        //String id = "";
        //try {
        //    TokenUtils.verify(token);
        //    id = TokenUtils.getTokenInfo(token).getClaim("user").asString();
        //    userProperties.put("id", id);
        //} catch (Exception err) {
        //    id = "未知用户" + CharUtil.randomVerify();
        //    userProperties.put("unknownId", id);
        //}
    }

    /**
     * 初始化端点对象,也就是被@ServerEndpoint所标注的对象
     */
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return super.getEndpointInstance(clazz);
    }


}
