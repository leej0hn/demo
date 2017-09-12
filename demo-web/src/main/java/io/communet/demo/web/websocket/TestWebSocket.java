package io.communet.demo.web.websocket;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/12
 * <p>Version: 1.0
 */
@ServerEndpoint("/websocket")
@Component
@Slf4j
public class TestWebSocket {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<TestWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen (Session session){
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        List<String> tokenList = requestParameterMap.get("token");
        List<String> clientWxIdList = requestParameterMap.get("clientWxId");
        String clientWxId = "";
        if( tokenList != null  ){
            String token = tokenList.get(0);
            if( token != null && token.equals("b53a132adb7294e7c71771e60b4eaabe") ){
                webSocketSet.add(this);
                addOnlineCount();
                if( clientWxIdList != null ){
                    clientWxId = clientWxIdList.get(0);
                }
                log.info("有新链接加入 ! " + " clientWxId : " + clientWxId + " ; 当前在线人数为" + getOnlineCount());
                return;
            }
        }
        try {
            session.close();
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }
    }

    @OnClose
    public void onClose (){
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一链接关闭!当前在线人数为" + getOnlineCount());
    }

    @OnMessage(maxMessageSize = 50000 )
    public void onMessage (String message, Session session) throws IOException {
        log.info("来自客户端的消息:" + message);
    }

    public static synchronized  int getOnlineCount (){
        return TestWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        TestWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        if( TestWebSocket.onlineCount <= 0 ){
            TestWebSocket.onlineCount = 0;
        }else {
            TestWebSocket.onlineCount--;
        }
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
