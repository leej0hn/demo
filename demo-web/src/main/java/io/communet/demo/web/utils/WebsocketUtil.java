package io.communet.demo.web.utils;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/13
 * <p>Version: 1.0
 */
public class WebsocketUtil {

    private static ConcurrentMap<String,Session> webSocketMap = new ConcurrentHashMap<>();

    public synchronized static void put(String key ,Session session){
        webSocketMap.put(key,session);
    }

    public synchronized static Session get(String key){
        return webSocketMap.get(key);
    }

    public synchronized static void remove(String key){
        webSocketMap.remove(key);
    }

    public synchronized static int size(){
        return webSocketMap.size();
    }

}
