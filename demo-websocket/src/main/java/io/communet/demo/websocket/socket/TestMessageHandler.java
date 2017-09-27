package io.communet.demo.websocket.socket;

import com.alibaba.fastjson.JSON;
import io.communet.demo.websocket.dto.WechatMsg;
import io.communet.demo.websocket.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.MessageHandler;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/14
 * <p>Version: 1.0
 */
@Slf4j
@Component
public class TestMessageHandler implements MessageHandler.Whole<String> {

    @Override
    public void onMessage(String message) {
        WechatMsg wechatMsg = JSON.parseObject(message, WechatMsg.class);
        if( !wechatMsg.getApiCode().equals("9999")){
            log.info("TestMessageHandler hash : " + this.hashCode() + "  testService hashcode : " +  " 当前在线人数为" + WebsocketUtil.size() + "  来自客户端的消息:" + message);
        }
    }

}
