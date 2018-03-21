package io.communet.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 2018/02/21
 * <p>Version: 1.0
 */
@SpringBootApplication
@Slf4j
public class DemoWebSocket2Application implements CommandLineRunner {

    @Value("${applicationName}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoWebSocket2Application.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        log.info("{} boot successfully", this.applicationName);
        countDownLatch.await();
    }

}