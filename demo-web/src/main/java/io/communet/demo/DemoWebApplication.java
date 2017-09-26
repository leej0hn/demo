package io.communet.demo;

import com.alibaba.fastjson.JSON;
import io.communet.demo.web.utils.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.websocket.Session;
import java.util.concurrent.CountDownLatch;

/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 16/7/8
 * <p>Version: 1.0
 */
@SpringBootApplication
@Slf4j
public class DemoWebApplication implements CommandLineRunner {

    @Value("${dubbo.name}")
    private String dubboName;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoWebApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        log.info("{} boot successfully", this.dubboName);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        try {
                            Session session = WebsocketUtil.get("wxid_d7c1vhp6q1xv22");
                            WechatMsg wechatMsg = new WechatMsg();
                            wechatMsg.setClientId("8642802ab611607b89cad5d257d90a45");
                            wechatMsg.setChatRoomId("6420656522@chatroom");
//                            wechatMsg.setTalker("Lee_John");
//                            wechatMsg.setApiCode("9007");
//                            wechatMsg.setContent("999999");
//                            wechatMsg.setApiCode("9009");
//                            wechatMsg.setFileUrl("http://qzs-dev.oss-cn-shenzhen.aliyuncs.com/wechat-helper/207b18cd63c14ccba3302b311e506102.jpg");
                            wechatMsg.setApiCode("9008");
                            wechatMsg.setFileUrl("http://qzs-dev.oss-cn-shenzhen.aliyuncs.com/wechat-helper/d298bf4252f046dc86b2424fe31d4eea.amr");

                            session.getBasicRemote().sendText(JSON.toJSONString(wechatMsg));
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
        };
        new Thread(runnable).start();
        countDownLatch.await();
    }

}