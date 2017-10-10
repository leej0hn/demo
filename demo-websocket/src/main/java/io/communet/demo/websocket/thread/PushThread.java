package io.communet.demo.websocket.thread;

import com.alibaba.fastjson.JSON;
import io.communet.demo.websocket.dto.WechatMsg;
import io.communet.demo.websocket.utils.WebsocketUtil;

import javax.websocket.Session;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/9/27
 * <p>Version: 1.0
 */
public class PushThread extends Thread {

    @Override
    public void run(){
        try {
            while (true) {
                try {
                    Session session = WebsocketUtil.get("wxid_d7c1vhp6q1xv22");
                    if( session != null ) {
                        WechatMsg wechatMsg = new WechatMsg();
                        wechatMsg.setClientId("8642802ab611607b89cad5d257d90a45");
//                        wechatMsg.setChatRoomId("6420656522@chatroom");
                            wechatMsg.setTalker("Lee_John");
//                            wechatMsg.setApiCode("9007");
//                            wechatMsg.setContent("999999");
                        wechatMsg.setApiCode("9009");
                        wechatMsg.setFileUrl("http://qzs-dev.oss-cn-shenzhen.aliyuncs.com/wechat-helper/207b18cd63c14ccba3302b311e506102.jpg");
//                    wechatMsg.setApiCode("9008");
//                    wechatMsg.setFileUrl("http://qzs-dev.oss-cn-shenzhen.aliyuncs.com/wechat-helper/d298bf4252f046dc86b2424fe31d4eea.amr");
//                    wechatMsg.setApiCode("9010");
//                    wechatMsg.setFileUrl("http://www.hao123.com");

                        session.getBasicRemote().sendText(JSON.toJSONString(wechatMsg));
                    }
                    Thread.sleep(20000);
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.sleep(10000);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
