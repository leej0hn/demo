package io.communet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 16/7/8
 * <p>Version: 1.0
 */
@SpringBootApplication
public class DemoWebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoWebApplication.class);
        application.run(args);
    }

}