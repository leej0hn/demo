package io.communet.demo;

import io.communet.demo.persistence.mongo.repository.impl.SimpleCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.CountDownLatch;


/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 16/7/8
 * <p>Version: 1.0
 */
@SpringBootApplication
@Slf4j
@EnableMongoRepositories(repositoryBaseClass = SimpleCustomerRepository.class)
public class DemoServiceApplication  implements CommandLineRunner {

    @Value("${dubbo.name}")
    private String dubboName;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoServiceApplication.class,
                "classpath:/spring/dubbo-provider.xml","classpath:/spring/dubbo-consumer.xml");
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        log.info("{} boot successfully", this.dubboName);
        countDownLatch.await();
    }

}