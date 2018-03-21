package io.communet.demo.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import static io.communet.demo.websocket.configuration.WebSocketConfiguration.socketMap;

/**
 * Websocket建立连接监听器
 *
 * @author wxw
 * @date 2017-12-29 16:07
 */
@Slf4j
@Component
public class WebsocketConnectListener implements ApplicationListener<SessionConnectEvent> {

    /**
     * 头名称KEY
     */
    private static final String headerNameKey = "roomId";

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        log.debug("WebsocketConnectedListener:"+event.getMessage());
        //获取Session连接信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        //获取群ID
        if(sha.containsNativeHeader(headerNameKey)) {
            String roomId = sha.getFirstNativeHeader(headerNameKey);
            //获取SessionId
            String sessionId = sha.getSessionId();
            //注册sessionId
            socketMap.put(roomId, sessionId);
            log.info("roomId : " + roomId + " ; " + " sessionId : " + sessionId);
        }
    }
}
