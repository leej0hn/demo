package io.communet.demo.web.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/13
 * <p>Version: 1.0
 */
@Slf4j
public class WebsocketUtil {

    private static ConcurrentMap<String,Session> webSocketMap = new ConcurrentHashMap<>();

    public synchronized static void put(String key ,Session session){
        try{
            //踢掉相同的
            Session sessionOld = webSocketMap.get(key);
            if( sessionOld != null ){
                sessionOld.close();
            }
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }

        webSocketMap.put(key,session);
    }

    public static Session get(String key){
        return webSocketMap.get(key);
    }

    public static void remove(String key){
        webSocketMap.remove(key);
    }

    public static int size(){
        return webSocketMap.size();
    }

    public static String remove(Session session){
        String key = null;
        if( webSocketMap.size() > 0  ){
            for (Map.Entry<String, Session> entry : webSocketMap.entrySet()) {
                Session forSession = entry.getValue();
                if( forSession.getId().equals(session.getId())){
                    key = entry.getKey();
                    webSocketMap.remove(key);
                }
            }
        }
        return key;
    }

}
