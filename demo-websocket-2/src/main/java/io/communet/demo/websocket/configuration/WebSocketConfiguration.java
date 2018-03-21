package io.communet.demo.websocket.configuration;

import io.communet.demo.websocket.handler.WebSocketErrorHandler;
import io.communet.demo.websocket.listener.WebSocketDisconnectListener;
import io.communet.demo.websocket.listener.WebsocketConnectListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket配置信息
 *
 * @author wxw
 * @date 2017-12-29 15:52
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    public static Map<String,String> socketMap = new ConcurrentHashMap();

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加一个/ichater端点，客户端就可以通过这个端点来进行连接；withSockJS作用是添加SockJS支持
        registry.setErrorHandler(webSocketHandler()).addEndpoint("/ichater/wxWs").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //定义了一个客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        config.enableSimpleBroker("/getRoomMsg","/getPersonalMsg");
        //设置前缀默认是user可以修改，点对点时使用
        config.setUserDestinationPrefix("/ichater/wxWs");
    }

    /**
     * WebSocket 连接
     *
     * @return
     */
    @Bean
    public WebsocketConnectListener websocketConnectListener() {
        return new WebsocketConnectListener();
    }

    /**
     * WebSocket 断开连接
     *
     * @return
     */
    @Bean
    public WebSocketDisconnectListener webSocketDisconnectListener() {
        return new WebSocketDisconnectListener();
    }

    /**
     * Websocket Error处理
     *
     * @return
     */
    @Bean
    public StompSubProtocolErrorHandler webSocketHandler() {
        return new WebSocketErrorHandler();
    }
}
