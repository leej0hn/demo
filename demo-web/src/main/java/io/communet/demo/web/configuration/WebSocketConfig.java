package io.communet.demo.web.configuration;

import io.communet.demo.web.websocket.TestWebSocket;
import io.communet.demo.web.websocket.handler.TestMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/12
 * <p>Version: 1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter (){
        return new ServerEndpointExporter();
    }

    @Bean
    public ServerEndpointRegistration wechatWebSocketSingleton() {
        return new ServerEndpointRegistration("/wechat/websocket", new TestWebSocket(messageHandlerSingleton()));
    }

    @Bean
    public TestMessageHandler messageHandlerSingleton(){
        return new TestMessageHandler();
    }
}
