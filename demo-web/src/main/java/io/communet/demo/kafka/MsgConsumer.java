package io.communet.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/1
 * <p>Version: 1.0
 */
@Component
@Slf4j
public class MsgConsumer {
    @KafkaListener(topics = {"my-replicated-topic","my-replicated-topic2"})
    public void processMessage(String content) {
        log.info("kafka MsgConsumer : " + content);
    }
}
