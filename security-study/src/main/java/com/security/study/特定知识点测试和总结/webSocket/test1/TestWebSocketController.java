package com.security.study.特定知识点测试和总结.webSocket.test1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/testWebSocket")
public class TestWebSocketController {


    @Resource
    WebSocketServer webSocketServer;

    @RequestMapping("/send")
    public void sendMsg() throws InterruptedException {
        for(;;){
            TimeUnit.SECONDS.sleep(3);
            System.out.println("发送一次---");
            webSocketServer.sendToAll(String.valueOf(new Random().nextInt(1000)));
        }
    }
}
