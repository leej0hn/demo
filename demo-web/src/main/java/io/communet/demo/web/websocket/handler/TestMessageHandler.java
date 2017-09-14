package io.communet.demo.web.websocket.handler;

import io.communet.demo.service.TestService;
import io.communet.demo.web.component.SpringContextHolder;
import io.communet.demo.web.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.MessageHandler;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/14
 * <p>Version: 1.0
 */
@Slf4j
public class TestMessageHandler implements MessageHandler.Whole<String> {
    private TestService testService = (TestService) SpringContextHolder.getBean("testService");

    @Override
    public void onMessage(String message) {
        log.info("TestMessageHandler hash : " + this.hashCode() + "  testService hashcode : " + testService.hashCode() + " 当前在线人数为" + WebsocketUtil.size() + "  来自客户端的消息:" + message);
    }

}
