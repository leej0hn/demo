package io.communet.demo.web.controller;

import io.communet.demo.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Set;

import static io.communet.demo.websocket.configuration.WebSocketConfiguration.socketMap;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/01/11
 * <p>Version: 1.0
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/api/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response index() {
        return Response.ok("test");
    }

    @GetMapping("/page/test")
    public ModelAndView testPage() {
        String test = "Test Page , hello world ";
        return new ModelAndView("testPage","test",test);
    }

    @RequestMapping(value = "/api/pushws/room", method = RequestMethod.GET)
    public void pushwsRoom(){
        //获取roomId
        String roomId = "roomid-test";
        //获取SessionId
        String sessionId = socketMap.get(roomId);
        log.info("/api/pushws  sessionId : " + sessionId);
        this.template.convertAndSendToUser(sessionId,"/getRoomMsg", "test pushwsRoom info...", createHeaders(sessionId));
    }

    @RequestMapping(value = "/api/pushws/persional", method = RequestMethod.GET)
    public void pushwsPersonal(){
        //获取roomId
        String roomId = "roomid-test";
        //获取SessionId
        String sessionId = socketMap.get(roomId);
        log.info("/api/pushws  sessionId : " + sessionId);
        this.template.convertAndSendToUser(sessionId,"/getPersonalMsg", "test pushwsPersonal info...", createHeaders(sessionId));
    }

    /**
     * 设置消息头
     *
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }


}
