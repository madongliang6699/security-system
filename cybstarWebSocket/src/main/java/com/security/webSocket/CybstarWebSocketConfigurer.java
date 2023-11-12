package com.security.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * WebSocketConfig配置
 */
@Configuration
@EnableWebSocket
public class CybstarWebSocketConfigurer implements WebSocketConfigurer {

    /**
     * 因为websocket技术客户端和服务端是一直链接着的，所以在集群情况下不需要考虑客户端找不到自己的session在哪个服务器上的问题。
     * 也就是说，虽然在集群环境下，虽然session分布在不同的服务器里面，但是因为客户端一旦与服务端建立了连接就一直连接着，
     * 所以不需要担心客户端下次发送消息的时候找不到自己的session在哪个服务器上。
     * 基于上面的逻辑，如果只是客户端与服务端之间进行数据实时推送，这种springboot与websocket的简单的集成就已经可以满足集群需求了。
     * 【目前公司的业务也应该只是这样的需求。】
     *
     * 但是另外一种业务需求就需要考虑集群环境下的session分散问题了，那就是，不同客户端之间通过session相互发送消息的场景。
     * 当客户端A需要给客户端B发送消息的时候（类似微信发消息），客户端A需要把数据发到自己在服务端的session中，A的session再把数据发给B的session，
     * B的session再把数据发给客户端B。而集群下的sessionA和sessionB可能不在同一个jvm中，那怎么通信呢？这就要借助其他中间件了（比如rocketMQ），
     * sessionA把数据以mq消息的形式投递到mq消息中间件中。其他服务器都去消费这个消息，消费到消息之后，根据消息关键信息，判断是否是要发给当前服务器map中的某个session的。
     * 如果是，就由这个session处理消息。这样就做到session之间的互通了。
     *
     * 可以写成@EnableCybstarWebSocket的形式，参数是启用那种集群通讯模式（redis或mq），还有参数是控制哪些业务类型不做token验证。
     */



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry
                //添加Handler消息处理对象，和websocket访问地址
                .addHandler(cybstarWebSocketHandler(), "/ws")
                //设置允许跨域访问
                .setAllowedOrigins("*")
                //添加拦截器,实现用户链接前进行权限校验等操作
                .addInterceptors(new CybstarWebSocketInterceptor())
        ;


    }

    @Bean
    public WebSocketHandler cybstarWebSocketHandler() {
        return new CybstarWebSocketHandler();
    }


}
