package io.communet.demo.web.websocket.handler;

import com.alibaba.fastjson.JSON;
import io.communet.demo.WechatMsg;
import io.communet.demo.service.TestService;
import io.communet.demo.web.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TestService testService ;

    @Override
    public void onMessage(String message) {
        WechatMsg wechatMsg = JSON.parseObject(message, WechatMsg.class);
        if( !wechatMsg.getApiCode().equals("9999")){
            log.info("TestMessageHandler hash : " + this.hashCode() + "  testService hashcode : " + testService.hashCode() + " 当前在线人数为" + WebsocketUtil.size() + "  来自客户端的消息:" + message);
        }
    }

}
