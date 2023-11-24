package com.security.study.webSocket.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;


/**
 * WebSocketConfig配置
 */
@Configuration
@EnableWebSocket
public class MyWebSocketConfig implements WebSocketConfigurer {

    /**
     * 注入拦截器
     */
    @Resource
    private WebSocketInterceptor webSocketInterceptor;
    @Resource
    private WebSocketInterceptor2 webSocketInterceptor2;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry
                //添加myHandler消息处理对象，和websocket访问地址
                .addHandler(myHandler1(), "/ws")
                .addHandler(myHandler2(), "/wsWithout")
                //.setHandshakeHandler()
                //设置允许跨域访问
                .setAllowedOrigins("*")
                //添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(webSocketInterceptor)
                //.addInterceptors(webSocketInterceptor, webSocketInterceptor2)
        ;


    }

    @Bean
    public WebSocketHandler myHandler1() {
        return new MyWebSocketHandler();
    }

    @Bean
    public WebSocketHandler myHandler2() {
        return new MyWebSocketHandler2();
    }
}
