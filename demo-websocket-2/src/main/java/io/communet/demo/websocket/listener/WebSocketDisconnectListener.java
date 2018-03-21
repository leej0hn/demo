package io.communet.demo.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static io.communet.demo.websocket.configuration.WebSocketConfiguration.socketMap;

/**
 * Websocket断开连接监听器
 *
 * @author wxw
 * @date 2017-12-29 16:15
 */
@Slf4j
@Component
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.debug("WebSocketDisconnectListener:"+event.getMessage());
        //获取Session连接信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //获取SessionId
        String sessionId = sha.getSessionId();
        log.info(" sessionId : " + sessionId);
        //sessionId获取roomId
        String roomId = socketMap.get(sessionId);
        //如果roomId不为空，则移除sessionId
        if(StringUtils.isNotBlank(roomId)) {
            //移除sessionId
            socketMap.remove(roomId);
            //移除roomId
            socketMap.remove(sessionId);
        }
    }
}
