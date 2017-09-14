package io.communet.demo.web.websocket;

import com.google.common.base.Throwables;
import io.communet.demo.web.utils.WebsocketUtil;
import io.communet.demo.web.websocket.handler.TestMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/12
 * <p>Version: 1.0
 */
@Slf4j
public class TestWebSocket extends Endpoint{

    private final TestMessageHandler testMessageHandler;

    public TestWebSocket(TestMessageHandler testMessageHandler){
        this.testMessageHandler = testMessageHandler;
        log.info("TestWebSocket hashcode : " + this.hashCode());
    }

    @Override
    public void onOpen (Session session ,EndpointConfig endpointConfig){
        try {
            Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
            List<String> tokenList = requestParameterMap.get("token");
            List<String> clientWxIdList = requestParameterMap.get("clientWxId");
            String clientWxId = null ;
            if( tokenList != null  ){
                String token = tokenList.get(0);
                if( token != null && token.equals("b53a132adb7294e7c71771e60b4eaabe") ){
                    if( clientWxIdList != null ){
                        clientWxId = clientWxIdList.get(0);
                    }
                    if( clientWxId != null && !clientWxId.equals("")){
                        WebsocketUtil.put(clientWxId,session);
                        session.addMessageHandler(testMessageHandler);
                        log.info("TestWebSocket onOpen hashcode : " + this.hashCode() + "  sessionId : " + session.getId() +  " clientWxId : " + clientWxId + "   有新链接加入 !  ; 当前在线人数为" + WebsocketUtil.size());
                        return;
                    }
                }
            }
            session.close();
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public void onClose (Session session,CloseReason closeReason){
        String clientWxId = WebsocketUtil.remove(session);
        log.info("sessionId : " + session.getId() + "  clientWxId : " + clientWxId + "  有一链接关闭!当前在线人数为" + WebsocketUtil.size());
    }

    @OnMessage(maxMessageSize = 50000 )
    public void onMessage (String message, Session session) throws IOException {
//        log.info("sessionId : " + session.getId() + " clientWxId  : " + clientWxId + " 当前在线人数为" + WebsocketUtil.size() + "  来自客户端的消息:" + message);
    }


}
/** android 客户端 代码
 使用 autobahn-0.5.0.jar包

 import de.tavendo.autobahn.WebSocketConnection;
 import de.tavendo.autobahn.WebSocketException;
 import de.tavendo.autobahn.WebSocketHandler;

 private static final String wsurl = "ws://192.168.0.19:8082/websocket?username=1&passwd=2";

 private void connect() {
    Log.i(TAG, "ws connect....");
    try {
        mConnect.connect(wsurl, new WebSocketHandler() {
            @Override
            public void onOpen() {
                Log.i(TAG, "Status:Connect to " + wsurl);
            }

            @Override
            public void onTextMessage(String payload) {
                Log.i(TAG, payload);
            }

            @Override
            public void onClose(int code, String reason) {
                    Log.i(TAG, "Connection lost..");
            }
        });
    } catch (WebSocketException e) {
        e.printStackTrace();
    }
 }

 *
 */
